package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;

class LoxFunction implements LoxCallable {
  private final Stmt.Function declaration;
  private Environment closure;

  private final boolean isInitializer;

  LoxFunction(Stmt.Function declaration, Environment closure,
              boolean isInitializer) {
    this.isInitializer = isInitializer;
    this.closure = closure;
    this.declaration = declaration;
  }

  public Environment getClosure() {return closure;}

  static LoxFunction noOp(Environment closure, int paramSize){
    Token noOp = new Token(TokenType.FUN, "skip", null, 0);
    List<Token> params = new ArrayList<Token>();
    for(int i = 0; i< paramSize; i++){
      params.add(new Token(TokenType.NIL, "empty", null, 0));
    }
    List<Stmt> body =  new ArrayList<Stmt>();
    Stmt.Function empty = new Stmt.Function(noOp, params, body);
    
    return new LoxFunction(empty, closure, false);
  }

  LoxFunction bind(LoxInstance instance) {
    Environment environment = new Environment(closure);
    environment.define("this", instance);
    return new LoxFunction(declaration, environment,
                           isInitializer);
  }

  //@Overload
  LoxFunction bind(LoxInstance instance, LoxFunction inner){
    Environment environment = new Environment(closure);
    environment.define("this", instance);
    environment.define("inner", inner);
    return new LoxFunction(declaration, environment, isInitializer);
  }

  @Override
  public String toString() {
    return "<fn " + declaration.name.lexeme + ">";
  }
  @Override
  public int arity() {
    return declaration.params.size();
  }
  @Override
  public Object call(Interpreter interpreter,
                     List<Object> arguments) {
    Environment environment = new Environment(closure);

    for (int i = 0; i < declaration.params.size(); i++) {
      environment.define(declaration.params.get(i).lexeme,
          arguments.get(i));
    }

    try {
      interpreter.executeBlock(declaration.body, environment);
    } catch (Return returnValue) {
      if (isInitializer) return closure.getAt(0, "this");

      return returnValue.value;
    }

    if (isInitializer) return closure.getAt(0, "this");
    return null;
  }
}
