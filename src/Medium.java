import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Medium {
    public static void main(String[] args) {
        int[] arr = new int[]{-1, 0, 1, 2, -1, -4};
//        System.out.println(Arrays.toString(arr));
        System.out.println(threeSum(arr));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();
        int[] checkArr = new int[3];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length && j != i; j++) {
                for (int k = 0; k < nums.length && k != i && k != j; k++) {
                    checkArr[0] = nums[i];
                    checkArr[1] = nums[j];
                    checkArr[2] = nums[k];
                    if (checkArr[0] + checkArr[1] + checkArr[2] == 0) {
                        List<Integer> valuesList = new ArrayList<Integer>(Arrays.asList(checkArr[0], checkArr[1], checkArr[2]));
                        Collections.sort(valuesList);
                        int count = 0;
                        for (List<Integer> elem:finalList) {
                            if (elem.equals(valuesList)){
                                count++;
                            }
                        }
                        if (count == 0) {
                            finalList.add(valuesList);
                        }
                    }
                }
            }
        }
        return finalList;
    }

}
