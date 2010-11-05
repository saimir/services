/**
 * This document is a part of the source code and related artifacts
 * for CollectionSpace, an open source collections management system
 * for museums and related institutions:
 *
 * http://www.collectionspace.org
 * http://wiki.collectionspace.org
 *
 * Copyright (c) 2009 Regents of the University of California
 *
 * Licensed under the Educational Community License (ECL), Version 2.0.
 * You may not use this file except in compliance with this License.
 *
 * You may obtain a copy of the ECL 2.0 License at
 * https://source.collectionspace.org/collection-space/LICENSE.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.collectionspace.services.IntegrationTests.xmlreplay;

import java.util.ArrayList;
import java.util.List;

/**
 * User: laramie
 * $LastChangedRevision:  $
 * $LastChangedDate:  $
 */
public class ServiceResult {
    public String testID = "";
    public String testGroupID = "";
    public String fullURL = "";
    public String deleteURL = "";
    public String location = "";
    public String CSID = "";
    public String subresourceCSID = "";
    public String result = "";
    public int responseCode = 0;
    public String responseMessage = "";
    public String method = "";
    public String error = "";
    public String fromTestID = "";
    public String auth = "";
    public List<Integer> expectedCodes = new ArrayList<Integer>();
    public boolean gotExpectedResult(){
        for (Integer oneExpected : expectedCodes){
            if (responseCode == oneExpected){
                return true;
            }
        }
        if (0<responseCode && responseCode<200){
            return false;
        } else if (400<=responseCode) {
            return false;
        }
        return true;
    }
    public static final String[] DUMP_OPTIONS = {"minimal", "detailed", "full"};
    public String toString(){
        return detail(true);

    }
    public String detail(boolean includePayloads){
        return "{ServiceResult: "
                + ( Tools.notEmpty(testID) ? " testID:"+testID : "" )
                + ( Tools.notEmpty(testGroupID) ? "; testGroupID:"+testGroupID : "" )
                + ( Tools.notEmpty(fromTestID) ? "; fromTestID:"+fromTestID : "" )
                +"; "+method
                +"; "+responseCode
                + ( Tools.notEmpty(responseMessage) ? "; msg:"+responseMessage : "" )
                +"; URL:"+fullURL
                +"; auth: "+auth
                + ( Tools.notEmpty(deleteURL) ? "; deleteURL:"+deleteURL : "" )
                + ( Tools.notEmpty(location) ? "; location.CSID:"+location : "" )
                + ( Tools.notEmpty(error) ? "; ERROR:"+error : "" )
                + ( (expectedCodes.size()>0) ? "; expectedCodes:"+expectedCodes : "" )
                + "; gotExpected:"+gotExpectedResult()
                + ( includePayloads && Tools.notEmpty(result) ? "; result:"+result : "" )
                +"}";
    }
    public String minimal(){
        return "{ServiceResult: "
                + ( Tools.notEmpty(testID) ? testID : "" )
                +"; "+method
                +"; "+responseCode
                + ( Tools.notEmpty(responseMessage) ? "; msg:"+responseMessage : "" )
                +"; URL:"+fullURL
                +"; auth: "+auth
                + ( Tools.notEmpty(error) ? "; ERROR:"+error : "" )
                + "; gotExpected:"+gotExpectedResult()
                +"}";
    }
    public String dump(String which){
        if (which.equalsIgnoreCase(DUMP_OPTIONS[0])){
            return minimal();
        } else if (which.equalsIgnoreCase(DUMP_OPTIONS[1])){
            return detail(false);
        } else if (which.equalsIgnoreCase(DUMP_OPTIONS[2])){
            return detail(true);
        }
        else return toString();
    }
}