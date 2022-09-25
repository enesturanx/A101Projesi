package com.testinium.step;

import com.testinium.driver.Driver;
import com.testinium.methods.Methods;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepImp {

    Methods methods;

    LinkedHashMap<String, String> productDetails = new LinkedHashMap<String, String>();

    int number;

    public StepImp(){
        methods = new Methods();
    }

    @Step("<saniye> saniye bekle")
    public void saniyeBekle(long saniye) {
        methods.saniyeKadarBekle(saniye);
    }

    @Step("<saniye> bekle")
    public void waitSeconds(int saniye) throws InterruptedException{
        methods.waitForsecond(saniye);
    }

    @Step("<milisaniye> milisaniye bekle")
    public void milisaniyeBekle(long milisaniye){
        methods.milisaniyeKadarBekle(milisaniye);
    }

    @Step("<key> url git")
    public void urlGit(String key) {
        methods.urlGit(key);
    }

    @Step("<key> elementine tikla")
    public void elementeTikla(String key) {
            methods.tikla(key);
    }

    @Step("<key> elementine tikla mobil")
    public void elementeTiklaMobil(String key){
        methods.tiklaMobil(key);
    }

    @Step("Mouse u <key> elementi üzerine getir.")
    public void mouseHover(String key){
        methods.mouseHover(key);
    }

    @Step("<element> elementine <text> degerini yaz")
    public void writeText(String element, String text){
        methods.metinYolla(element,text);
    }


    @Step("<element> elementine <text> degerini yaz mobil")
    public void writeTextMobil(String element, String text){
        methods.metinYollaMobil(element,text);
    }

    @Step("<element> element listesinden random element seç ve tıkla")
    public void randomElement(String element){
        methods.randomElementFromList(element);
    }


    @Step("<key> elementinin gorunur olmasi kontrol edilir")
    public void elementGorunurluguKontrol(String key) {
            methods.gorunurlukKontrol(key);
    }

    @Step("<key> elementinin gorunur olmasi kontrol edilir mobil")
    public void elementGorunurluguKontrolMobil(String key) {
        methods.gorunurlukKontrolMobil(key);
    }

    @Step("<key> elementinin tiklanabilir olmasi kontrol edilir")
    public void elementTiklanabilirlikKontrol(String key) {
        methods.tiklanabilirlikKontrol(key);
    }

    @Step("<key> elementinin tiklanabilir olmasi kontrol edilir mobil")
    public void elementTiklanabilirlikKontrolMobil(String key) {
        methods.tiklanabilirlikKontrolMobil(key);
    }


    @Step("Su anki url <url> ile ayni mi")
    public void urlEsitMi(String url) {
        assertEquals(methods.suankiURL(),url,"URL eşit değil");
    }

    @Step("<url> URL kontrol")
    public void urlKontrol(String url) {
        while(!Objects.equals(url, methods.suankiURL())){
            methods.milisaniyeKadarBekle(250);
        }
        System.out.println("URL'ler eşit");
    }

    @Step("<key> elementine odaklan")
    public void focusElement(String key) {
        methods.elementeOdak(key);
    }


    @Step("<key> elementinin text degeri <expectedText> degerine esit mi")
    public void controlElementText(String key, String expectedText) {

        String actualText = methods.textDegeriniAl(key)
                .replace("\r", "").replace("\n", "").trim();
        System.out.println("Beklenen text: " + expectedText);
        System.out.println("Alınan text: " + actualText);
        assertEquals(expectedText, actualText,"Text değerleri eşit değil");
        System.out.println("Text değerleri eşit");
    }

    @Step("<key> elementinin text degeri <expectedText> degerini iceriyor mu mobil")
    public void controlElementTextContain(String key, String expectedText) {

        String actualText = methods.textDegeriniAlMobil(key)
                .replace("\r", "").replace("\n", "").trim();
        System.out.println("Beklenen text: " + expectedText);
        System.out.println("Alınan text: " + actualText);
        assertTrue(actualText.contains(expectedText),"Text değerini icermiyor");
    }

    @Step("<key> li select elementinin <text> textini sec")
    public void selectText(String key, String text){
        methods.selectByText(key,text);
    }

    @Step("<key> li select elementinin <text> textini sec mobil")
    public void selectTextMobil(String key, String text){
        methods.selectByTextMobil(key,text);
    }

    @Step("<key> li elemente random mail gir")
    public void randomMail(String key){
        String text = "enes" + methods.randomNumber(99999) + "@a101.com";
        methods.metinYolla(key,text);
    }

    @Step("<key> li elemente random mail gir mobil")
    public void randomMailMobil(String key){
        String text = "enes" + methods.randomNumber(99999) + "@a101.com";
        methods.metinYollaMobil(key,text);
    }

    @Step("scroll yap")
    public void scrollElementMobil(){
        methods.scrollMobil();
    }
}
