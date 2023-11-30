package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class LoxClass extends LoxInstance implements LoxCallable {
  final String name;
  final LoxClass superclass;
  private final Map<String, LoxFunction> methods;
  private LoxClass child;

  LoxClass(String name, LoxClass superclass,
           Map<String, LoxFunction> methods) {
    super(null);
    this.superclass = superclass;
    this.name = name;
    this.methods = methods;
    this.child = null;
    setKlass(this);
  }
  LoxFunction findMethod(String name) {
    if (methods.containsKey(name)) {
      return methods.get(name);
    }

    if (superclass != null) {
      return superclass.findMethod(name);
    }

    return null;
  }

  LoxFunction findMethod(String name, LoxFunction inner){
    if(methods.containsKey(name)){
      LoxFunction method = methods.get(name);
      method = ( method == inner ) ? method.bind(this, LoxFunction.noOp(method.getClosure(), method.arity())) : method.bind(this, inner);
      return method;
    }

    return null;
  }

  public LoxClass getChild(){
    return this.child;
  }

  public void setChild(LoxClass childClass){
    this.child = childClass;
  }

  @Override
  public String toString() {
    return name;
  }
  @Override
  public Object call(Interpreter interpreter,
                     List<Object> arguments) {
    LoxInstance instance = new LoxInstance(this);
    LoxFunction initializer = findMethod("init");
    if (initializer != null) {
      initializer.bind(instance).call(interpreter, arguments);
    }

    return instance;
  }

  @Override
  public int arity() {
    LoxFunction initializer = findMethod("init");
    if (initializer == null) return 0;
    return initializer.arity();
  }
}
