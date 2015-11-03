package dong.com.weixin.utils;

/**
 * Created by dong on 2015/10/9.
 */
public class aa {


        /**主方法*/
        public static void main(String[] args) {
            //声明数组
            int[] nums = {27, 8, 57, 9, 23, 41, 65, 19, 0, 1, 2, 4, 5};
            //应用快速排序方法
            quickSort(nums, 0, nums.length-1);
            //显示排序后的数组
            for(int i = 0; i < nums.length; ++i) {
                System.out.print(nums[i] + ",");
            }
            System.out.println("");
        }

        /**快速排序方法*/
        public static void quickSort(int[] a, int lo0, int hi0) {
            int lo = lo0;
            int hi = hi0;

            if (lo >= hi)
                return;

            //确定指针方向的逻辑变量
            boolean transfer=true;

            while (lo != hi) {
                if (a[lo] > a[hi]) {
                    //交换数字
                    int temp = a[lo];
                    a[lo] = a[hi];
                    a[hi] = temp;
                    //决定下标移动，还是上标移动
                    transfer = (transfer == true) ? false : true;
                }

                //将指针向前或者向后移动
                if(transfer)
                    hi--;
                else
                    lo++;

                //显示每一次指针移动的数组数字的变化
      /*for(int i = 0; i < a.length; ++i) {
        System.out.print(a[i] + ",");
      }
      System.out.print(" (lo,hi) = " + "(" + lo + "," + hi + ")");
      System.out.println("");*/
            }

            //将数组分开两半，确定每个数字的正确位置
            lo--;
            hi++;
            quickSort(a, lo0, lo);
            quickSort(a, hi, hi0);
        }
    }

