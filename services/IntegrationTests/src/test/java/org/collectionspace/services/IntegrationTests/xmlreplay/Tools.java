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

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import  java.util.regex.Pattern;
import java.util.regex.Matcher;

/** General utility methods.
 *   @author Laramie Crocker
 */
public class Tools {
    /** @return first glued to second with the separator string, at most one time - useful for appending paths.
     */
    public static String glue(String first, String separator, String second){
        if (first==null) { first = ""; }
        if (second==null) { second = ""; }
        if (separator==null) { separator = ""; }
        if (first.startsWith(separator) && second.startsWith(separator)){
            return first.substring(0, first.length()-separator.length()) + second;
        }
        if (first.endsWith(separator) || second.startsWith(separator)){
            return first+second;
        }
        return first+separator+second;
    }

    /** Handles null strings as empty.  */
    public static boolean isEmpty(String str){
        return !notEmpty(str);
    }

    /** Handles null strings as empty.  */
        public static boolean notEmpty(String str){
        if (str==null) return false;
        if (str.length()==0) return false;
        return true;
    }

    /** Handles null strings as false.  */
    public static boolean isTrue(String test){
        return notEmpty(test) && (new Boolean(test)).booleanValue();
    }

                    /*  Example usage of searchAndReplace:
                        for (Map.Entry<String,String> entry : variablesMap.entrySet()){
                            String key = entry.getKey();
                            String replace = entry.getValue();
                            String find = "\\$\\{"+key+"\\}";   //must add expression escapes
                                                                //because $ and braces are "special", and we want to find "${object.CSID}"
                            uri = Tools.searchAndReplace(uri, find, replace);
                            System.out.println("---- REPLACE.uri:        "+initURI);
                            System.out.println("---- REPLACE.find:       "+find);
                            System.out.println("---- REPLACE.replace:    "+replace);
                            System.out.println("---- REPLACE.uri result: "+uri);
                        }
                    */
    public static String  searchAndReplace(String source, String find, String replace){
        Pattern pattern = Pattern.compile(find);
        Matcher matcher = pattern.matcher(source);
        String output = matcher.replaceAll(replace);
        return output;
    }



}
