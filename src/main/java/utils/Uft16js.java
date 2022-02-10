package utils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * <p>
 *  uft16表情格式转化
 * </p>
 *
 * @author wpc
 * @since 2021/11/23
 */

public class Uft16js {
    public String trans(String str){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try{
            engine.eval("function uncodeUtf16(str){" +
                    "      var reg = /&#.*?;/g;" +
                    "      var result = str.replace(reg,function(char){" +
                    "          var H,L,code;" +
                    "         if(char.length == 9 ){" +
                    "              code = parseInt(char.match(/[0-9]+/g));" +
                    "              H = Math.floor((code-0x10000) / 0x400)+0xD800;" +
                    "              L = (code - 0x10000) % 0x400 + 0xDC00;" +
                    "              return unescape(\"%u\"+H.toString(16)+\"%u\"+L.toString(16));" +
                    "         }else{" +
                    "             return char;" +
                    "         }" +
                    "     });" +
                    "     return result;" +
                    "}");
            if (engine instanceof Invocable) {
                Invocable in = (Invocable) engine;
                return (String) in.invokeFunction("uncodeUtf16",str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
