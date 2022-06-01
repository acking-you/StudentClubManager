package util;

public class CharacterUtil {
    private static boolean isValidNameChar(char x){
        boolean isHanzi = false;
        boolean isEnglish = false;
        boolean isNumber = false;
        if(x >= '\u4e00' && x <= '\u9fa5'){
            isHanzi = true;
        }
        if((0x0041<=x&&x<=0x005A)||(0x0061<=x&&x<=0x007A)){
            isEnglish = true;
        }
        if (x <= '9' && x >= '0') {
            isNumber = true;
        }
        return isHanzi||isEnglish||isNumber;
    }

    public static boolean isValidNameString(String name){
        for (int i = 0; i < name.length(); i++) {
            if (!isValidNameChar(name.charAt(i) )){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidSidString(String name){
        if(name.charAt(0)!='B')return false;
        for (int i=1;i<name.length();i++){
            if (name.charAt(i)<'0'&&name.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
