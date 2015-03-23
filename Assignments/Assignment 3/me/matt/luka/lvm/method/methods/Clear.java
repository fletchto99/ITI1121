package me.matt.luka.lvm.method.methods;

import me.matt.luka.exception.LukaSyntaxException;
import me.matt.luka.interfaces.Stack;
import me.matt.luka.lvm.method.LukaMethod;
import me.matt.luka.lvm.method.MethodsContext;
import me.matt.luka.wrappers.Token;

public class Clear extends LukaMethod {

    @Override
    public boolean canExecute(final Token t, final Stack<Token> stack) {
        if (t.getSymbol().equalsIgnoreCase("clear")) {
            if (stack.isEmpty()) {
                throw new LukaSyntaxException("Stack cannot be empty");
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    @Override
    public boolean execute(final MethodsContext context) {
        while (!context.getStack().isEmpty()) {
            context.getStack().pop();
        }
        return true;
    }

}
