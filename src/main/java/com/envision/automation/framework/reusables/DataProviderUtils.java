package com.envision.automation.framework.reusables;

import com.envision.automation.application.testDataManager.RandomDataGenerator;
import com.envision.automation.framework.configurations.ConfigurationLoader;
import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider(name ="LoginDataProvider")
    public static Object[][] getLoginData(){
        return new Object[][]{

                {"hey@abc.com","Testing@1234"}

        };
    }
    @DataProvider(name ="randomLoginDataProvider")
    public static Object[][] getRandomLoginData(){

        int noOfData = ConfigurationLoader.configOptions.getNoOfDataSets();
        Object[][] objects =new  Object[noOfData][2];
        for (int row=0;row<noOfData;row++){
            objects[row][0]= RandomDataGenerator.getUsername();
            objects[row][1]= RandomDataGenerator.getUniquePassword();
        }
        return objects;
    }
}
