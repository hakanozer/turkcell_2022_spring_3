package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class XmlService {

    public ArrayList<Currency> xml() {
        ArrayList<Currency> ls = new ArrayList<>();
       try {
           String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
           String data = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
           Document doc = Jsoup.parse(data, Parser.xmlParser());
           Elements elements = doc.getElementsByTag("Currency");
           for(Element item : elements) {
               String currencyName = item.getElementsByTag("CurrencyName").text();
               String forexBuying = item.getElementsByTag("ForexBuying").text();
               String forexSelling = item.getElementsByTag("ForexSelling").text();
               Currency c = new Currency(currencyName, forexBuying, forexSelling);
               ls.add(c);
           }
       }catch (Exception ex) {
           System.err.println("xml Error : " + ex);
       }
       return ls;
    }


}
