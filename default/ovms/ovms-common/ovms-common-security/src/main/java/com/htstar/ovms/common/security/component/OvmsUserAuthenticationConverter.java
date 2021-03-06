package com.htstar.ovms.common.security.component;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.htstar.ovms.common.core.constant.CommonConstants;
import com.htstar.ovms.common.core.constant.SecurityConstants;
import com.htstar.ovms.common.security.exception.OvmsAuth2Exception;
import com.htstar.ovms.common.security.service.OvmsUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author ovms
 * @date 2019-03-07
 * <p>
 * 根据checktoken 的结果转化用户信息
 */
@Slf4j
public class OvmsUserAuthenticationConverter implements UserAuthenticationConverter {
	private static final String N_A = "N/A";

	/**
	 * Extract information about the user to be used in an access token (i.e. for resource servers).
	 *
	 * @param authentication an authentication representing a user
	 * @return a map of key values representing the unique information about the user
	 */
	@Override
	public Map<String, ?> convertUserAuthentication(Authentication authentication) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put(USERNAME, authentication.getName());
		if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
			response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		}
		return response;
	}

	/**
	 * Inverse of {@link #convertUserAuthentication(Authentication)}. Extracts an Authentication from a map.
	 *
	 * @param responseMap a map of user information
	 * @return an Authentication representing the user or null if there is none
	 */
	@Override
	public Authentication extractAuthentication(Map<String, ?> responseMap) {
		if (responseMap.containsKey(USERNAME)) {
			Collection<? extends GrantedAuthority> authorities = getAuthorities(responseMap);
			Map<String, ?> map = MapUtil.get(responseMap, SecurityConstants.DETAILS_USER, Map.class);
			validateTenantId(map);
			String username = MapUtil.getStr(map, SecurityConstants.DETAILS_USERNAME);
			Integer id = MapUtil.getInt(map, SecurityConstants.DETAILS_USER_ID);
			Integer deptId = MapUtil.getInt(map, SecurityConstants.DETAILS_DEPT_ID);
			Integer etpId = MapUtil.getInt(map, SecurityConstants.DETAILS_ETP_ID);
			String phone = MapUtil.getStr(map, SecurityConstants.DETAILS_PHONE);
			String avatar = MapUtil.getStr(map, SecurityConstants.DETAILS_AVATAR);
			String roleCodesStr = MapUtil.getStr(map, SecurityConstants.ROLE_CODES);
            String[] roleCodes=null;
			if(StrUtil.isNotBlank(roleCodesStr)){
                JSONArray roleCodesObj = JSONUtil.parseArray(roleCodesStr);
                roleCodes=new String[roleCodesObj.size()] ;
                for (int i = 0; i < roleCodesObj.size(); i++) {
                    roleCodes[i]= (String) roleCodesObj.get(i);
                }
            }

			OvmsUser user = new OvmsUser(id, deptId, phone, avatar, etpId, username, N_A, true
					, true, true, true, authorities,roleCodes);
			return new UsernamePasswordAuthenticationToken(user, N_A, authorities);
		}
		return null;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
		Object authorities = map.get(AUTHORITIES);
		if (authorities instanceof String) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
		}
		if (authorities instanceof Collection) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
					.collectionToCommaDelimitedString((Collection<?>) authorities));
		}
		throw new IllegalArgumentException("Authorities must be either a String or a Collection");
	}

	private void validateTenantId(Map<String, ?> map) {
		String headerValue = getCurrentTenantId();
		Integer userValue = MapUtil.getInt(map, SecurityConstants.DETAILS_ETP_ID);
		if (StrUtil.isNotBlank(headerValue) && !userValue.toString().equals(headerValue)) {
			log.warn("请求头中的租户ID({})和用户的租户ID({})不一致", headerValue, userValue);
			// TODO: 不要提示租户ID不对，可能被穷举 --flrtoken中加入正确的企业ID
			throw new OvmsAuth2Exception(SpringSecurityMessageSource.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.badTenantId", "Bad tenant ID"));
		}
	}

	private Optional<HttpServletRequest> getCurrentHttpRequest() {
		return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
				.filter(requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
				.map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
				.map(ServletRequestAttributes::getRequest);
	}

	private String getCurrentTenantId() {
		return getCurrentHttpRequest().map(httpServletRequest -> httpServletRequest.getHeader(CommonConstants.ETP_ID)).orElse(null);
	}
}
