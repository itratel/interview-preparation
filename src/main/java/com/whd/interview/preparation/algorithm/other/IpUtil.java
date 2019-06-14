package com.whd.interview.preparation.algorithm.other;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

/**
 * @author whd.java@gmail.com
 * @date 2019/06/14 22:05
 * @apiNote Describe the function of this class in one sentence
 */
@UtilityClass
public class IpUtil {


    private static String clientIp = "192.168.0.107";

    /****
     * 将IP地址转化为Integer数组
     * @param ip
     * @return
     */
    private Integer[] splitIpToInt(String ip) {
        String[] ips = StrUtil.split(ip, ".");
        return Arrays.stream(ips).map(Integer::parseInt).toArray(Integer[]::new);
    }


    public boolean isIpaccess(IpDto dto) {

        return false;
    }

    public static void main(String[] args) {
        IpDto dto = new IpDto();
        clientIp = "192.168.10.102";
        if (isBetweenStartAndEndIp(clientIp, dto.getStartIp(), dto.getEndIp())) {
            System.out.println(clientIp + "是合法的ip地址");
        } else {
            System.out.println(clientIp + "不是合法的ip地址");
        }
    }

    /***
     * 判断当前IP是否在指定IP范围内
     * @param clientIp
     * @param startIp
     * @param endIp
     * @return
     */
    private boolean isBetweenStartAndEndIp(String clientIp, String startIp, String endIp) {
        if (StrUtil.isAllBlank(startIp, endIp)) {
            return true;
        }
        Integer[] clientIps = splitIpToInt(clientIp);
        Integer[] startIps = splitIpToInt(startIp);
        Integer[] endIps = splitIpToInt(endIp);
        for (int i = 0; i < clientIps.length; i++) {
            if (startIps[i] > endIps[i]) {
                System.out.println("开始IP地址大于结束IP地址");
                return true;
            }
            //8(startIps[i]) < clientIps[i] || clientIps[i] < 56(endIps[i]) 表明IP在指定范围内，直接返回true
            if (clientIps[i] > startIps[i] && clientIps[i] < endIps[i]) {
                return true;
            }
            //56(endIps[i]) < clientIps[i] || clientIps[i] < 12(startIps[i]) 表明IP不在指定范围内，直接返回false
            if (clientIps[i] > endIps[i] || clientIps[i] < startIps[i]) {
                return false;
            }
            //当startIps[i] == clientIps[i] == endIps[i]时，跳出本次循环，进入下一次循环
            if (clientIps[i].equals(startIps[i]) && clientIps[i].equals(endIps[i])) {
                //当循环到第四个码段时，表明当前IP地址和开始地址以及结束地址是一样的，就返回true
                if (i == clientIps.length - 1) {
                    return true;
                }
                continue;
            }

            if (clientIps[i].equals(startIps[i]) && clientIps[i] < endIps[i]) {
                if (i == clientIps.length - 1) {
                    return true;
                }
                int j = i + 1;
                if (clientIps[j] < startIps[j]) {
                    return false;
                } else if (clientIps[j] > startIps[j]) {
                    return true;
                }
            }

            if (clientIps[i].equals(endIps[i]) && clientIps[i] > startIps[i]) {
                if (i == clientIps.length - 1) {
                    return true;
                }
                int j = i + 1;
                if (clientIps[j] > endIps[j]) {
                    return false;
                } else if (clientIps[j] < endIps[j]) {
                    return true;
                }
            }

        }
        return false;
    }

    /***
     * 判断当前IP是否在指定固定IP当中
     * @param clientIp
     * @param ips
     * @return
     */
    private boolean isInIps(String clientIp, List<String> ips) {
        if (ips.isEmpty()) {
            return true;
        }
        for (String ip : ips) {
            if (StrUtil.isNotBlank(ip) && clientIp.equals(ip)) {
                return true;
            }
        }
        return false;
    }


    @Data
    static class IpDto {

        private String clientIp = IpUtil.clientIp;

        private String startIp = "192.168.10.100";

        private String endIp = "192.169.11.100";

        private String firstFixedIp = "192.168.0.108";

        private String secondFixedIp = "192.168.0.109";

        private String thirdFixedIp = "192.168.0.110";

    }


}
