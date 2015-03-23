package me.matt.luka.lvm.method.methods;

import me.matt.luka.interfaces.Stack;
import me.matt.luka.lvm.method.LukaMethod;
import me.matt.luka.lvm.method.MethodsContext;
import me.matt.luka.wrappers.Token;

public class Print extends LukaMethod {

    @Override
    public boolean canExecute(final Token t, final Stack<Token> stack) {
        return t.getSymbol().equalsIgnoreCase("pstack");
    }

    @Override
    public boolean execute(final MethodsContext context) {
        System.out.println(context.getStack());
        return true;
    }

}
