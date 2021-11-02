package com.example.demo.webservice;

import com.example.demo.utils.HttpClient;
import com.example.demo.utils.WebServiceUtils;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class TestWebService {

    @org.junit.jupiter.api.Test
    public void test1() throws DocumentException, UnsupportedEncodingException {
        String url = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<soapenv:Envelope xmlns:ernesto=\"http://WebXml.com.cn/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:getRegionCountry/>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        String s = WebServiceUtils.doPostByXml("http://www.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl", url);
        System.out.println(s);
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(new ByteArrayInputStream(s.getBytes("UTF-8")));
        HashMap nsMap = new HashMap();
        nsMap.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        nsMap.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        nsMap.put("xsd", "http://www.w3.org/2001/XMLSchema");
        nsMap.put("ns", "http://WebXml.com.cn/");
        //获取子节点
        XPath xsub = read.createXPath("/soap:Envelope/soap:Body/ns:getRegionCountryResponse/ns:getRegionCountryResult");
//        XPath xsub=read.createXPath("//string");
        xsub.setNamespaceURIs(nsMap);
        Element element = (Element) xsub.selectSingleNode(read);
        List<Element> elements = element.elements();
        List<String> list = new ArrayList<>();
        for (Element e : elements) {
//            System.out.println("值======="+e.getText());
            list.add(e.getText());
        }
        for (String aa : list) {
            System.out.println(aa);
        }

    }


    @org.junit.jupiter.api.Test
    public void test2() throws UnsupportedEncodingException, DocumentException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:getSupportCityString>\n" +
                "         <web:theRegionCode>北京</web:theRegionCode>\n" +
                "      </web:getSupportCityString>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        String s = WebServiceUtils.doPostByXml("http://www.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl", xml);
        System.out.println(s);

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new ByteArrayInputStream(s.getBytes("UTF-8")));
        HashMap nsMap = new HashMap();
        nsMap.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        nsMap.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        nsMap.put("xsd", "http://www.w3.org/2001/XMLSchema");
        nsMap.put("ns", "http://WebXml.com.cn/");
        XPath xPath = document.createXPath("/soap:Envelope/soap:Body/ns:getSupportCityStringResponse/ns:getSupportCityStringResult");
        xPath.setNamespaceURIs(nsMap);
        Element element = (Element) xPath.selectSingleNode(document);
        List<Element> elements = element.elements();
        List<String> list = new ArrayList<>();
        for (Element e : elements) {
            list.add(e.getText());
        }
        for (String aa : list) {
            System.out.println(aa);
        }


    }


    @Test
    public void test3() throws UnsupportedEncodingException, DocumentException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:getWeather>\n" +
                "         <web:theCityCode>792</web:theCityCode>\n" +
                "         <web:theUserID>11</web:theUserID>\n" +
                "      </web:getWeather>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        String s = WebServiceUtils.doPostByXml("http://www.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl", xml);
        System.out.println(s);

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new ByteArrayInputStream(s.getBytes("UTF-8")));
        HashMap nsMap = new HashMap();
        nsMap.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        nsMap.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        nsMap.put("xsd", "http://www.w3.org/2001/XMLSchema");
        nsMap.put("ns", "http://WebXml.com.cn/");
        XPath xPath = document.createXPath("/soap:Envelope/soap:Body/ns:getWeatherResponse/ns:getWeatherResult");
        xPath.setNamespaceURIs(nsMap);
        Element element = (Element) xPath.selectSingleNode(document);
        List<Element> elements = element.elements();
        List<String> list = new ArrayList<>();
        for (Element e : elements) {
            list.add(e.getText());
        }
        for (String aa : list) {
            System.out.println(aa);
        }
    }
}