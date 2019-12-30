package com.buzz.jwtdemo.enumerate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumMapper {
	
	private Map<String,List<EnumMapperValue>> factory = new LinkedHashMap<>();
	
	public EnumMapper(){}
	
	//코드 enum 클래스를 Map 에 추가
	public void put(String key,Class<? extends EnumMapperType> e) {
		factory.put(key, toEnumValues(e));
	}
	
	//enum class 의 key,value 값을 List<EnumMapperValue> 타입으로 반환
	private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperType> e){
		return Arrays.stream(e.getEnumConstants())
				.map(EnumMapperValue::new)
				.collect(Collectors.toList());
	}
	
	public List<EnumMapperValue> get(String key){
		return factory.get(key);
	}
	
	public Map<String, List<EnumMapperValue>> get(List<String> keys){
		if(keys==null || keys.size() == 0) {
			return new LinkedHashMap<>();
		}
		
		return keys.stream().collect(Collectors.toMap(Function.identity(), key-> factory.get(key)));
	}
	
	public Map<String,List<EnumMapperValue>> getAll(){
		return factory;
	}
}
