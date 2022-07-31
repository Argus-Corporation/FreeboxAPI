package net.argus.api.freebox;

import java.util.HashMap;
import java.util.Map;

public abstract class Permission <T extends Enum<?>, V extends Object> {
	
	private Map<T, V> mapPerm = new HashMap<T, V>();
	
	public Permission(Map<T, V> map) {
		this.mapPerm = map;
	}
	
	@Override
	public String toString() {
		return mapPerm.toString();
	}
	
	public V test(T perm) {
		return mapPerm.get(perm);
	}

}
