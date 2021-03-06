
import java.util.Arrays;
import java.util.Random;

/**
 * @author youshishuia
 * @date 2019/03/28
 */
public class Sort {
    public static void main(String[] args) {
        int [] a = new int[10];
        Random random = new Random();
        for(int i =0;i<a.length;i++){
            a[i] = random.nextInt(100);
        }

        quickSort(a);
        System.out.println(Arrays.toString(a));
        mergeSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * @description 直接插入排序 算法复杂度 平均O(n²) 最好O(n) 最坏O(n²) 空间辅助O(1) 稳定
     * @param [a] 整形数组
     * @return void
     **/
    public static void insertSort(int[] a) {
        //如果数组为空就返回
        if(a==null){ return; }
        //取得数组长度
        int len = a.length;
        int j   = 0;
        int min = 0;
        //取第一个元素为有序序列，剩下的为无序序列,每次循环有序序列插入一个元素
        for(int i=0;i<len;i++){
            min = a[i];
            //从无序序列第一个元素开始（对有序序列从后往前扫描）找相应的位置插入
           for(j=i;j>0 &&min<a[j-1];j--){
               //如果当前值min比前一个元素小，则把前一个位置向后挪一位，递归
               a[j] = a[j-1];
               }
           //min比前面元素大，就当前位置插入
            a[j] = min;
           }
        }
    /**
     * @description 冒泡排序
     * @param [a] 整形数组
     * @return void
     **/
    public static void bSort(int[]a){
        if(a==null){return;}
        int len = a.length;
        for(int i=0;i<len;i++){
            for(int j=len-1;j>0;j--){
                if(a[j]<a[j-1]){
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }
    /**
     * @description 选择排序
     * @param [a] 整形数组
     * @return void
     **/
    public static void selectSort(int[]a){
        int len = a.length;
        int min ;
        int index ;
        for(int i=0;i<len;i++){
            min = a[i];
            index = i;
            for(int j =i+1 ;j<len-1;j++){
                if(a[j]<min){
                   min = a[j];
                   index = j;
                }
            }
            a[index] = a[i];
            a[i] = min;
        }
    }
    /**
     * @description 对一个数组快排，判断数组是否为空
     * @param [a] 整形数组
     * @return void
     **/
    public static void quickSort(int[]a){
        //如果数组为空就跳出
        if(a==null){return;}
        //递归排序方法
        sort(a,0,a.length-1);
    }
    /**
     * @description 快排的递归实现
     * @param [a, low, hight] 整形数组 低指针 高指针
     * @return void
     **/
    public static void sort (int[]a,int low,int high){
            if (low < high) {
                int middle = getMiddle(a, low, high);
                sort(a, low, middle - 1);
                sort(a, middle + 1, high);
            }
    }
    /**
     * @description 快排并返回基准下标
     * @param [a, low, high] 整形数组 低指针 高指针
     * @return int 返回基准下标
     **/
    public static int getMiddle(int[]a,int low,int high){
        //取数组第一位做基准值
        int middle = a[low];
        int mid = low;
        while(low<high){
            while(low<high && a[high]>middle){
                high--;
            }
            a[low] = a[high];
            while(low<high && a[low]<middle){
                low++;
            }
            a[high] = a[low];
        }
        //当高低指针相遇时，将基准值赋给相遇位置，然后返回基准下标
        a[low] = middle;
        mid = low;
        return mid;
    }
    /**
     * @description 归并排序递归实现
     * @param [a, low, high]整形数组 低指针 高指针
     * @return void
     **/
    public static void mergeSort(int[]a,int low,int high){
        if(a==null){return;}
        int middle = (high+low)/2;
        if(low<high){
            mergeSort(a,low,middle);
            mergeSort(a,middle+1,high);
            merge(a,low,high,middle);
        }
    }
    /**
     * @description 归并合并实现
     * @param [a, low, high, middle] 整形数组 低指针 高指针 中间指针
     * @return int[]
     **/
    public  static void merge(int[]a, int low, int high,int middle){
        int [] temp = new int[high-low+1];
        //左指针
        int i = low;
        //右指针
        int j = middle+1;
        //辅助数组初始指针
        int k = 0;
        while(i<middle+1 && j<high+1){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }
            else{
                temp[k++] = a[j++];
            }
        }
        while(i<middle+1){
            temp[k++] = a[i++];
        }
        while(j<high+1){
            temp[k++] = a[j++];
        }
        for(int k2=0;k2<temp.length-1;k2++){
            a[low+k2] = temp[k2];
        }
    }
    public  static void heapSort(int[]a){
        for(int i =0;i<a.length;i++) {
            buildHeap(a, a.length-1 - i);
            int temp = a[0];
            a[0] = a[a.length-1-i];
            a[a.length-1-i] = temp;

        }
    }
    //对data数组从0到lastIndex建大顶堆
    public static void buildHeap(int[]a,int lastIndex){
        //从lastIndex处节点（最后一个节点）的父节点开始
        for(int i=(lastIndex-1)/2;i>=0;i--){
            //k保存正在判断的节点
            int k=i;
            //如果当前k节点的子节点存在
            while(k*2+1<=lastIndex){
                int biggerIndex =2*k+1;
                if(biggerIndex<lastIndex){
                    if(a[biggerIndex]<a[biggerIndex+1]){
                        biggerIndex++;
                    }
                }
                if(a[k]<a[biggerIndex]){
                    int temp = a[k];
                    a[k] = a[biggerIndex];
                    a[biggerIndex] = temp;
                    k = biggerIndex;
                }
                else {
                    break;
                }
            }
        }
    }
}