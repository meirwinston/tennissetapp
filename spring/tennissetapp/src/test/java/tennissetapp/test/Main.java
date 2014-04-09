package tennissetapp.test;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

public class Main {

	static interface A{
		
	}
	static class B implements A{
		
	}
	public static void main(String[] args) {
		ClassToInstanceMap<A> numberDefaults = MutableClassToInstanceMap.create();
//		numberDefaults.putInstance(A.class, new A());
		numberDefaults.putInstance(B.class, new B());
		
	}

}
