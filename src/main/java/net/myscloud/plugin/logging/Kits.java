package net.myscloud.plugin.logging;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 工具类
 *
 * @author Genesis
 * @since 1.0
 */
public final class Kits {

    public static String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            //不处理
        }
        return "UnknownHost";
    }

}
