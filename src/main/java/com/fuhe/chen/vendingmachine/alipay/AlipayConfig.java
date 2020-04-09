package com.fuhe.chen.vendingmachine.alipay;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 支付宝相关参数
 */
public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016102400749511";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCBwGO+Fksun1tQrpruq6sPKXCSM2n96TSkzglNjsTvGbzNM+wV7y6gYL+xOyFVD10GQd/dEiZXGOOZ7D/r97T1RXLOUx4eO0Mv32XlVy3457e6mxNVpYWsv0NsIlGn0ceGOA1lPht2xo51cnLEQ4DjbLA/gRcDmrtRRB1XIUJFWPcAWXKeuyB971FWGUMbQx4LR4SmxjcTVZbAr+dCiqXOj38o9JEGpEIKusodnuePCjEqo4Dulb7PgGaYaOIQZhc9Vcwf0oZUSPIZVgs7ArzJxuLEmSBNgDxZasyW8d8TRzn+iv9fNsvDKlvk8ehfYeDdL8ZLbdfYB7Zag1IU7LjTAgMBAAECggEACBl1YHlNyiRCc//iRgHLVM/2afnxpNr44UzHQeMmofJOWdTi6N+cXoCLlsmlJAUofmqASfGHGSrBkmsv9k/pKOJj70M6/xId+JAOcUmG4iG801Y418DUXls2HzrpM/uhY9x94ZMF7d0pKWWyilPYaNnxCpXJVP1Esib5WeYGmAOEyZekByp9ZvTRItFN5bNnrWm88iUdl+fvZJe1ruqqZr0dMdKMEik0d6PBHRkqUJbacrcuPKryJlcdNC4KPxD+3XReny2V1sEnRtPDQI8fV5Bl5qEf9GaP6Ee/uSQ5ICyktune7WegYWPGzTu+WkdDRGaK6ffjhW4+YfpgUsm8wQKBgQD59kT8YzBQ+yOCsM0Y5gTFWxxNvkSO5AZgcYdT/+ghzNF9f345YTZSKZJQboFvJ0YLR7kpBu4HL0HMbicavHHNxKa/VLxC1hlVN2dXObPUCaF1o94T/D+UbmWt1Miz9iQojV24Csh/dA87UBlTbUdrpax6kbBvTxVFsgEJJ4dIcwKBgQCE4sFSU2n8KZxuXQ6Lg5H8xluAQUxrvBK+oVCbWySXkWKmLePbk1PdrfTthawOWo1UKEX1S0PrW9UAdYVlo7lYJhF6VI3onFkpXiTjqw32CVBzIisV+920EVaYKdpuO8BUXS2p/tqxnoFtfLhRfKvp8WBry5Ykbo30PhTh6pOWIQKBgAHH+nfd8RMypu8geWWrBe+uFYaMS4rFHzKCvJchpLLQ9dEyHmEkSzHMsAk4sF1tZzdfXHdpYDrW4F4njsnG+/yeYR3N6cWEJwwPaAIcHCbvpqHi+AsmagGRw9G4SXHW2C0dzMQp94HI/u7KRFPu3Gps9MyuyxYP9zPW7+Dz0if3AoGAbPcz5zoXMns8lxMfli9xSoGtDmJ9M+qoxECrRHlcbBz/c501txv8LU80BIEUMUD3mwbbNuVc8GYBJ7s2g73ZgfrVYiZrp05tnopgXYAqlpHUslRWWFSL29hL1cKcJsBAWe1+QGOHGRcMsqcOzWMZ5V/U3Q+4Mm+lX554qTgdhYECgYEAvi0qiRAPEG0O8oAlcIL5aCElu9J2G+bY3P3ZEMqFMWYczDuaF/g7Yos8t7yL479MV+DKsFHv8jlRVODlRVArs679woPGb0OPcVhMClU64NDJqgS2xb+b259rg9QlGwuwv6djy6MSP6QpSpw2SnIkW4fHj3xcpNg6iIxU3IkmRAo=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://s30453o259.zicp.vip/getNotify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://s30453o259.zicp.vip/index/";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "utf-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAldKY1gjHSZdXyRMRqy+w55Kt7haYmug8S5qL8h2x+InXFUccPPtLf07dOOff+kAYQSLHj552WJHqW47zqUTFDCe7T9Naqlsy25f3fZpOsdIRWrFlcS6wy1WHYl5JFCqXlJM9eoO7f4gTVKZyu8GD5T2Fs9U2y2Um1V9HG6m+TWqdbMjX7MigblagqkPZ9/JesggEqEwYSWAXrshFovXP3fgbKsJHQFk9w8LLjlZhkho+HJEqm28C010QFjcIFRDdm+fFHC/e0QDSqIfy6MCaIt6Bg6oczHwaFeMepQfacJE4LeeTsrmq70LR75CttX+ipVNO1XolGSjintlq9tfA4wIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";


    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
