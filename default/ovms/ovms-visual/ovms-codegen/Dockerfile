FROM pig4cloud/java:8-jre

MAINTAINER wangiegie@gmail.com

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /ovms-codegen

WORKDIR /ovms-codegen

EXPOSE 5003

ADD ./target/ovms-codegen.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar ovms-codegen.jar
