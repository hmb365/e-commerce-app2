package com.envision.automation.framework.reusables;


import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BaseAsserts {

    public static void ShouldBeEqual(String expected, String actual, String... messageOnFailure){ //var args String...
        if(messageOnFailure.length>0){
            Assert.assertEquals(expected,actual, messageOnFailure[0]);

        }else {
            Assert.assertEquals(expected, actual);
        }
    }


    public static void ShouldNoBeEqual(String expected, String actual, String... messageOnFailure){ //var args String...
        if(messageOnFailure.length>0){
            Assert.assertNotEquals(expected,actual, messageOnFailure[0]);
        }else {
            Assert.assertEquals(expected, actual);
        }
    }

    public static void ShouldBeFalse(boolean value, String... messageOnFailure){ //var args String...
        if(messageOnFailure.length>0){
            Assert.assertFalse(value, messageOnFailure[0]);
        }else {
            Assert.assertFalse(value);
        }
    }

    public static void ShouldBeTrue(boolean value, String... messageOnFailure){ //var args String...
        if(messageOnFailure.length>0){
            Assert.assertTrue(value, messageOnFailure[0]);
        }else {
            Assert.assertTrue(value);
        }
    }

    public static void ShouldBeDisplayed(WebElement element, String... messageOnFailure){ //var args String...
        if(messageOnFailure.length>0){
            Assert.assertTrue(element.isDisplayed(), messageOnFailure[0]);
        }else {
            Assert.assertTrue(element.isDisplayed());
        }
    }

    public static void ShouldBeNotDisplayed(WebElement element, String... messageOnFailure){ //var args String...
        if(messageOnFailure.length>0){
            Assert.assertFalse(element.isDisplayed(), messageOnFailure[0]);
        }else {
            Assert.assertFalse(element.isDisplayed());
        }
    }

    public static void TextShouldBeEqual(WebElement element,String expectedText, String... messageOnFailure){ //var args String...
        if(messageOnFailure.length>0){
            Assert.assertEquals(element.getText(),expectedText, messageOnFailure[0]);
        }else {
            Assert.assertEquals(element.getText(),expectedText);
        }
    }


    public static void ContainsText(WebElement element,String expectedText, String... messageOnFailure){ //var args String...
        if(messageOnFailure.length>0){
            Assert.assertTrue(element.getText().contains(expectedText), messageOnFailure[0]);
        }else {
            Assert.assertTrue(element.getText().contains(expectedText));
        }
    }




}
