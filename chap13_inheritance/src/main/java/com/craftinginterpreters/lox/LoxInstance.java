package com.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;

class LoxInstance {
  private LoxClass klass;
  private final Map<String, Object> fields = new HashMap<>();

  LoxInstance(LoxClass klass) {
    this.klass = klass;
  }

  public void setKlass(LoxClass klass){
      this.klass = klass;
    }

  public LoxClass getKlass(){
    return this.klass;
  }

  Object get(Token name) {
    if (fields.containsKey(name.lexeme)) {
      return fields.get(name.lexeme);
    }

    LoxFunction method = klass.findMethod(name.lexeme);

    if((klass.superclass != null) && (method!= null)) return get(name, method);

    if (method != null) return method.bind(this);

    throw new RuntimeError(name, // [hidden]
        "Undefined property '" + name.lexeme + "'.");
  }

  Object get(Token name, LoxFunction base){

    if(base == null)
      base = klass.findMethod(name.lexeme);
    //TODO: bind within findMethod, not in here
    if(klass.superclass != null){
      LoxFunction chain = klass.findMethod(name.lexeme, base);
      return klass.superclass.get(name, chain);
    }

    LoxFunction funk = klass.findMethod(name.lexeme);
    if(funk != null) 
      return funk.bind(this, base);
    else if(funk == null && base != null)
      return base;

    throw new RuntimeError(name, // [hidden]
        "Undefined property '" + name.lexeme + "'.");
  }
  void set(Token name, Object value) {
    fields.put(name.lexeme, value);
  }
  @Override
  public String toString() {
    return klass.name + " instance";
  }
}
