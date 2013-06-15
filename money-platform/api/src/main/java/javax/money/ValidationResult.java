/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.money;

import com.sun.org.apache.bcel.internal.generic.IFNULL;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Anatole
 */
public final class ValidationResult<T> implements Serializable{
    
    private T item;
    private Map<String, Object[]> issues = new HashMap<String, Object[]>();
    
    public ValidationResult(T item) {
        if(item==null){
            throw new IllegalArgumentException("item required.");
        }
        this.item = item;
    }
    
    public ValidationResult(ValidationResult<T> base) {
        if(base==null){
            throw new IllegalArgumentException("base required.");
        }
        this.item = base.getItem();
        this.issues.putAll(base.issues);
    }
    
    public void add(ValidationResult<T> result) {
        if(result==null){
            throw new IllegalArgumentException("result required.");
        }
        if(!(this.item == result.getItem())){
            throw new IllegalArgumentException("Incompatible results.");
        }
        this.issues.putAll(result.issues);
    }
    
    public Set<String> getIssueKeys(){
        return Collections.unmodifiableSet(issues.keySet());
    }
    
    public Object[] getIssueContext(String key){
        return this.issues.get(key).clone();
    }
    
    public boolean isValid(){
        return issues.isEmpty();
    }
    
    public void addIssue(String key, Object... context){
        if(key==null){
            throw new IllegalArgumentException("key required.");
        }
        if(context==null){
            throw new IllegalArgumentException("context required.");
        }
        this.issues.put(key, context.clone());
    }
    
    public T getItem(){
        return item;
    }
    
    
    
}