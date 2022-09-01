package com.envision.automation.framework.reusables;

import com.envision.automation.application.testDataManager.RandomDataGenerator;
import com.envision.automation.framework.configurations.ConfigurationLoader;
import org.testng.annotations.DataProvider;

public class DataProviderUtils {
    //Data Provider is always static method
    @DataProvider(name ="LoginDataProvider")
    public static Object[][] getLoginData(){//[][]2D arrays means multiple Row and multipart cloums
    return new Object[][]{

            {"hey@abc.com","Testing@1234"},
//            {"Invalidemail@abc.com","Testing@1234"},
//            {"hey@abc.com","InvaidPassword"},
//            {"Invalidemail@abc.com","InvaidPassword"},

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
