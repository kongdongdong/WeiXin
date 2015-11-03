package dong.com.weixin.utils;

import java.util.ArrayList;
import java.util.List;

import dong.com.weixin.R;
import dong.com.weixin.bean.Contant;

/**
 * Created by dong on 2015/10/8.
 */
public class ContantsUtil {

    static List<Contant> contants = new ArrayList<Contant>();
    static List<Contant> contantsTwo = new ArrayList<Contant>();
    static List<Contant> contantsThree = new ArrayList<Contant>();
    public static List<Contant> getContants(){
        contants.clear();
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("阿丽");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("刘德华");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("周杰伦");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("那英");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("哈林");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("王杰");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("谢娜");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("古巨基");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("黄晓明");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        for(int i=0 ; i<10 ; i++){
            Contant c = new Contant();
            c.setIcon(R.mipmap.con_icon);
            c.setName("逼哥");
            c.setFirstLitter(PinYin4jUtil.getStrHeadChar(c.getName()));
            contants.add(c);
        }
        //排序

        contantsTwo.clear();
        for(int num = 65; num <=90 ;num++ ){
            for(Contant c: contants){

                if(PinYin4jUtil.getCnASCII(c.getFirstLitter()) == num){

                    contantsTwo.add(c);
                }else{
                    //首字母不是大写字母
                    //contantsThree.add(c);
                }
            }


        }

        /*for(Contant c: contantsThree){
            contantsTwo.add(c);
        }*/

        return contantsTwo;
    }

    private static void quickSort(int start0,int end0) {
        int start = start0;
        int end = end0;
        //确定指针方向的逻辑变量
        boolean transfer=true;
        while(start != end){
            if(PinYin4jUtil.getCnASCII(contants.get(start).getFirstLitter()) > PinYin4jUtil.getCnASCII(contants.get(end).getFirstLitter())){
                //交换对象
                Contant s = contants.get(start);
                Contant e = contants.get(end);


                contants.remove(s);
                contants.add(start,e);

                contants.remove(end);
                contants.add(end,s);
                //决定下标移动，还是上标移动
                transfer = (transfer == true) ? false : true;
            }
            System.out.println(contants.toString());
            //将指针向前或者向后移动
            if(transfer){
                end--;
            }else{
                start++;
            }

            start--;
            end++;
            quickSort(start0,start);
            quickSort(end,end0);
        }

    }

}
