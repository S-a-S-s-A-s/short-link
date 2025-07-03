package net.xdclass.component;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.SmsConfig;
import net.xdclass.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class SmsComponent {

    private static final String UrlTemplate = "https://gyytz.market.alicloudapi.com/sms/smsSend?mobile=%s&templateId=%s&smsSignId=%s&param=%s";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SmsConfig smsConfig;

    public void send(String mobbile, String param){

        long beginTime = CommonUtil.getCurrentTimestamp();

        String url = String.format(UrlTemplate, mobbile, smsConfig.getTemplateId(), smsConfig.getSmsSignId(), param);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "APPCODE " + smsConfig.getAppCode());
        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        long endTime = CommonUtil.getCurrentTimestamp();

        log.info("耗时={}ms, url={}, body={}", endTime - beginTime, url, response);

        if(response.getStatusCode().is2xxSuccessful()){
            log.info("发送验证码成功");
        }else{
            log.error("发送验证码失败");
        }

    }

}
