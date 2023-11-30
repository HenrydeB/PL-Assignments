package com.craftinginterpreters.lox;

import java.util.List;

class LoxLambda implements LoxCallable{
    

    LoxLambda(Expr.Lambda declaration, Environment env){
        this.declaration = declaration;
        this.closure = env;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public String toString() {
        return "<fn>";
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);
    for (int i = 0; i < declaration.params.size(); i++) {
      environment.define(declaration.params.get(i).lexeme,
          arguments.get(i));
    }

    try {
      interpreter.executeBlock(declaration.body, environment);
    } catch (Return returnValue) {
      return returnValue.value;
    }
    return null;
    }
    private final Expr.Lambda declaration;
    private final Environment closure;
}