/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.logic;

import musicstore.datastructures.AccountInfo;

/**
 *
 * @author jablo
 */
public class AccountInfoRetriever {
    public static AccountInfo GetAccountInfo(int userID)
    {
        AccountInfo mockInfo = new AccountInfo();
        
        mockInfo.setCartState(3);
        mockInfo.setFirstName("Marcin");
        mockInfo.setLastName("Jabłoński");
        mockInfo.setId(1);
        mockInfo.setTotalPrice((float) 30.99);
        mockInfo.setAvatarPath("src/resources/avatars/sample.jpg");
        
        return mockInfo;
    }
}
