public class Mysort {
    public int[] arr={46,8,23,12,1,79,13};
    //直接插入排序
    public void insert(){
        for(int i=1;i<arr.length;i++){
            int tmp=arr[i];
            int j=i-1;
            for(;j>=0&&arr[j]>tmp;j--){
                arr[j+1]=arr[j];
            }
            arr[j+1]=tmp;
        }
    }

    //折半插入排序
    public void binaryInsert(){
        for(int i=1;i<arr.length;i++){
            int tmp=arr[i];
            int left=0;
            int right=i;
            while(left<right){
                int mid=(left+right)>>>1;
                if(arr[mid]<=tmp){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
            for(int j=i;j>left;j--){
                arr[j]=arr[j-1];
            }
            arr[left]=tmp;
        }
    }

    //希尔排序
    private void insertGap(int gap){
        for(int i=1;i<arr.length;i++){
            int tmp=arr[i];
            int j=i-gap;
            for(;j>=0&&arr[j]>tmp;j-=gap){
                arr[j+gap]=arr[j];
            }
            arr[j+gap]=tmp;
        }
    }
    public void shell(){
        int gap=arr.length;
        while(gap>1){
            insertGap(gap);
            gap>>>=1;
        }
        insertGap(1);
    }

    //选择排序
    private void swap(int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
    public void select(){
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[i]){
                    swap(i,j);
                }
            }
        }
    }


    //堆排序
    private void shelfdown(int size,int index){
        int left=2*index+1;
        while(left<size){
            int max=left;
            int right=2*index+2;
            if(right<size){
                if(arr[max]<arr[right])
                {
                    max=right;
                }
            }
            if(arr[index]>=arr[max]){
                break;
            }

            swap(max,index);
            index=max;
            left=2*index+1;
        }
    }
    private void createHeap(){
        for(int i=(arr.length-1)/2;i>=0;i--){
            shelfdown(arr.length,i);
        }
    }
    public void heapSort(){
        createHeap();
        for(int i=0;i<arr.length-1;i++){
            swap(0,arr.length-i-1);
            shelfdown(arr.length-i-1,0);
        }
    }

    //冒泡排序
    public void bubbleSort(){
        for(int i=0;i<arr.length-1;i++){
            boolean isSwap=true;
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    swap(j,j+1);
                    isSwap=false;
                }
            }
            if(isSwap){
                break;
            }
        }
    }

    //快速排序
    //1.递归
    public void fastSort(int left,int right){
        if(left>=right){
            return;
        }
        int val=partion3(left,right);
        fastSort(left,val-1);
        fastSort(val+1,right);
    }
    private int partion1(int left,int right){
        int key=arr[left];
        int i=left;
        int j=right;
        while(i<j){
           while(i<j&&arr[j]>=key){
               j--;
           }
           while(i<j&&arr[i]<=key){
               i++;
           }
           if(i<j){
               swap(i,j);
           }
        }
        swap(i,left);
        return i;
    }
    private int partion2(int left,int right){
        int key=arr[left];
        int i=left;
        int j=right;
        while(i<j){
            while(i<j&&arr[j]>=key){
                j--;
            }
            arr[i]=arr[j];
            while(i<j&&arr[i]<=key){
                i++;
            }
            arr[j]=arr[i];
        }
        arr[i]=key;
        return i;
    }
    private int partion3(int left,int right){
        int key=arr[left];
        int d=left+1;
        for(int i=left+1;i<=right;i++){
            if(arr[i]<key){
                swap(i,d);
                d++;
            }
        }
        swap(d-1,left);
        return d-1;
    }
    //非递归
    public void quickSort(){
        Stack<Integer> stack=new Stack<>();
        stack.push(arr.length-1);
        stack.push(0);
        while(!stack.empty()){
            int left=stack.pop();
            int right=stack.pop();
            if(left>=right){
                continue;
            }
            int val=partion3(left,right);
            stack.push(val-1);
            stack.push(left);
            stack.push(right);
            stack.push(val+1);
        }
    }

    //归并排序
    public void mergeSort(int low,int high){
        if(low>=high-1){
            return ;
        }
        int mid=(low+high)/2;
        mergeSort(low,mid);
        mergeSort(mid,high);
        merge(low,mid,high);
    }
    private void merge(int low,int mid,int high){
        int i=low;
        int j=mid;
        int length=high-low;
        int[] tmp=new int[length];
        int k=0;
        while(i<mid&&j<high){
            if(arr[i]<=arr[j]){
                tmp[k++]=arr[i++];
            }else{
                tmp[k++]=arr[j++];
            }
        }
        while(i<mid){
            tmp[k++]=arr[i++];
        }
        while(j<high){
            tmp[k++]=arr[j++];
        }
        for(int c=0;c<length;c++){
            arr[low+c]=tmp[c];
        }
    }

    public void print(){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%d ",arr[i]);
        }
        System.out.println();
    }
}
