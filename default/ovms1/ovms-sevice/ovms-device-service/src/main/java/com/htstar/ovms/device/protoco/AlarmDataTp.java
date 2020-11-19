package com.htstar.ovms.device.protoco;

import lombok.Data;

/**
 * Description: 警情类型 ALARM_DATA
 * Author: flr
 * Date: Created in 2020/6/19
 * Company: 航通星空
 * Modified By:
 */
@Data
public class AlarmDataTp {
    /**
     * 新警情标志	U8	1	=0 结束的警情
     * =1 新警情
     * 既有警情产生也有警情结束（既存在0或者1的标志）的警情有：
     * 超速、低电压、水温告警、停车未熄火、转速高、尾气超标、疲劳驾驶、MIL故障告警；
     * 只有警情产生（既只存在1的标志）的警情有：
     * 急加速、急减速、拖吊告警、上电告警、急变道、急转弯、危险驾驶、震动告警、断电告警、
     * 区域告警、紧急告警、碰撞告警、防拆告警、非法进入告警、非法点火告警、OBD剪线告警、点火告警、熄火告警
     */
    private Integer newAlarmFlag;

    /**
     * 警情类型	U8	1
     * = 0x01 超速告警
     * = 0x02 低电压告警
     * = 0x03 水温告警
     * = 0x04 急加速告警
     * = 0x05 急减速告警
     * = 0x06 停车未熄火告警
     * = 0x07 拖吊告警
     * = 0x08 转速高告警
     * = 0x09 上电告警
     * = 0x0A 尾气超标
     * = 0x0B 急变道告警
     * = 0x0C 急转弯告警
     * = 0x0D 疲劳驾驶告警
     * = 0x0E 断电告警
     * = 0x0F 区域告警
     * = 0x10 紧急告警
     * = 0x11 碰撞告警
     * = 0x12 防拆告警
     * = 0x13 非法进入告警
     * = 0x14 非法点火告警
     * = 0x15 OBD剪线告警
     * = 0x16 点火告警
     * = 0x17 熄火告警
     * = 0x18 MIL故障告警
     * = 0x19 未锁车告警
     * = 0x1A 未刷卡告警
     * = 0x1B 危险驾驶告警
     * = 0x1C 震动告警
     */
    private Integer alarmType;

    /**
     * 当前值	U16	2	当前值，根据不同的(2)的值，表示当前的不同含义值
     * 参数的单位参看3.3TLV描述列表的告警设置项的告警阀值(0x1001~0x101C)
     *
     * 对于是急加速和急减速
     * 该字段分成两个U8类型的数值,分两种情况：
     * A：212GL不接HT-196或213GL/E/N产生急加/减速时：
     * 高位U8是当前速度(KM/H)；
     * 低位U8是前秒速度(KM/H)；
     * B：212GL接HT-196或213GD/EP/L产生急加/减速时：
     * 高位U8是当前加速度(单位0.1g)；
     * 低位U8是固定为0xFF；
     *
     * 对于区域告警
     * 该字段分成两个U8类型数值
     * 其中高位U8是表示区域告警标志
     * =0x00 表示驶入区域告警
     * =0x01 表示驶出区域告警
     * 低位U8是区域编号
     *
     * 碰撞报警：第一个字节表示碰撞报警的方向，
     * =0x00 前
     * =0x01 后
     * =0x02 左
     * =0x03 右
     * 第二个字节预留，填充“0x00”
     */
    private Short alarmDesc;


    /**
     * 警情阈值	U16	2	根据不同的(2)的值，表示当前的不同含义值
     * 参数的单位参考3.3TLV描述列表的告警设置项的告警阀值(0x1001~0x101C)
     *
     * 对于区域告警，该字段置为0x0000
     */
    private Short alarmThreshold;
}