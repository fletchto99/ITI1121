package me.matt.luka.lvm.method.methods;

import me.matt.luka.lvm.method.LukaMethod;
import me.matt.luka.lvm.method.MethodsContext;
import me.matt.luka.wrappers.Token;

public class Define extends LukaMethod {

    @Override
    public boolean canExecute(Token t) {
        return t.getSymbol().equals("def");
    }

    @Override
    public boolean execute(MethodsContext context) {
        Token number = context.getStack().pop();
        Token variable = context.getStack().pop();
        context.getDictionary().put(variable.getSymbol().substring(1), number);
        return true;
    }

}
