{
	[高效]删除一个List里的重复的元素
	2,3,2,4,5,5,4,3,2 -> 2,3,4,5
	借助Set对元素的唯一性要求和Set无可比拟的优势：快速的查找能力和高效的防止重复的能力	
	这是典型的以空间换时间的做法（开辟一个HashSet用来确保唯一性）
}
import java.util.*;
public class removeDuplicate{
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < 100000; i++){
		    list.add(i);
		}
		long cur = System.currentTimeMillis();
		//commonMethod(list);
		removeDuplicate(list);
		// removeDuplicateWithOrder(list);
		System.out.println((System.currentTimeMillis() - cur) + "");		
	}

	//普通的去重方法
	private static void commonMethod(List<Integer> list){
		List<Integer> subList = new ArrayList<>();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Integer current = (Integer)it.next();
			if (!subList.contains(current)) {
				subList.add(current);
			} else {//subList中已经包含i了，直接删除
				it.remove();
			}
		}
	}

	// 巧妙利用HashSet的构造方法
	//（该构造方法接受一个Collection为参数，将Collection里的所有不重复的元素加入HashSet）
	// 最后list是无序的
	public static void removeDuplicate(List<Integer> list)   { 
    		HashSet<Integer> set = new HashSet<>(list); 
    		list.clear(); 
    		list.addAll(set); 
	} 

	// 最后list是有序的
	private static void removeDuplicateWithOrder(List<Integer> list)   { 
      		Set set = new HashSet<Integer>(); 
      		List<Integer> newList = new ArrayList<>(); 
   		for(Iterator iter  =  list.iterator(); iter.hasNext();)   { 
         		Integer element  =  (Integer)iter.next(); 
         		if(set.add(element)){
            			newList.add(element); 
         		} 
     		} 
     		list.clear(); 
     		list.addAll(newList); 
	} 
}
