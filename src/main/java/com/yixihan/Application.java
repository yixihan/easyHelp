package com.yixihan;


import com.yixihan.simpleBool;
import com.yixihan.simpleInt;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    //    private static final int NUBMER_OF_VARUABLE = 1023;
    public static ArrayList<Variable> varStorage = new ArrayList<>();
    public static ArrayList<Statement> labStorage = new ArrayList<>();

    public static String[] keywords = {"int", "bool", "true", "false", "vardef", "binexpr", "unexpr", "assign", "print", "skip", "block", "if", "while", "program", "execute", "list", "store", "load", "quit"};
    //    public static String[] intOperator = {"+","-","*","/",">",">=","<","<=","==","!="};
    public static String intOperator = "+-*/>=<!=";
    public static String[] boolOperator = {"&&", "||", "==", "!="};

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        ArrayList<Simple> simpleArr = new ArrayList<>();
        Simple simpleTemp = new Simple (null, null, null);
        ArrayList<ArrayList<String>> command = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>(); // For storing lines temporarily
        String state = "";
        boolean end = false;
        int lineNo = 0;

        // Welcome message of SIMPLE
        System.out.print("------ SIMPLE ------\n"
                + "Welcome to SIMPLE, a command-line-based interpreter.\n");

        while (!end) {
            // Read commands
            System.out.print(">");
            temp.addAll(Arrays.asList(s.nextLine().split(" ")));
            command.add(temp);

            // Define which statement to use by first word in args
            state = command.get(lineNo).get(0);
            switch (state){
                case "vardef": {
                    simpleTemp = vardef(command.get(lineNo));
                    if (simpleTemp == null) {
                        lineNo--;
                        break;
                    }
                    simpleArr.add(simpleTemp);

                    // For test
                    System.out.print(simpleArr.get(lineNo).getLabel() + " ");
                    if (isVarBool (simpleTemp)) {
                        System.out.print(simpleArr.get(lineNo).getBool().getName() + " ");
                        System.out.println(simpleArr.get(lineNo).getBool ().getValue());
                    } else if (!isVarBool (simpleTemp)) {
                        System.out.print(simpleArr.get(lineNo).getInt().getName() + " ");
                        System.out.println(simpleArr.get(lineNo).getInt().getValue());
                    }
                    break;
                }
//                case "binexpr": {
//                    binexpr;
//                    break;
//                }
                case "unexpr": {
                    Simple tmp = unexpr (command.get (lineNo), simpleArr);
                    if (tmp == null) {
                        lineNo--;
                        break;
                    }
                    simpleArr.add (tmp);
                    System.out.print(simpleArr.get(lineNo).getLabel() + " ");
                    if (isVarBool (tmp)) {
                        System.out.print(simpleArr.get(lineNo).getBool().getName() + " ");
                        System.out.println(simpleArr.get(lineNo).getBool ().getValue());
                    } else if (!isVarBool (tmp)) {
                        System.out.print(simpleArr.get(lineNo).getInt().getName() + " ");
                        System.out.println(simpleArr.get(lineNo).getInt().getValue());
                    }
                    break;
                }
                case "print": {
                    print (command.get (lineNo), simpleArr);
                    doPrint (command.get (lineNo).get (1), simpleArr);
                    break;
                }
//                case "skip": {
//                    skip;
//                    break;
//                }
//                case "4": {
//                    searchVar;
//                    break;
//                }
//                case "execute": {
//                    end = true;
//                    break;
//                }
                case "quit": {
                    end = true;
                    break;
                }
                default: {
                    System.out.println("!Invalid input!");
                }
            }
            temp.clear();
            lineNo++;
        }
        System.out.println("------ END ------");
    }

    // !!Haven't check keywords!!

    /**
     * Defining variables
     * Create a new variable and initialize, and check if the variable name is legal
     * Format: vardef lab typ varName expRef
     */
    public static Simple vardef(ArrayList<String> args){
        Pattern p = Pattern.compile("[a-zA-Z\\d]+"), p1 = Pattern.compile("\\d");
        Matcher mA = p.matcher(args.get(1)), mA1 = p1.matcher(args.get(1));
        Matcher mB = p.matcher(args.get(3)), mB1 = p1.matcher(args.get(3));

        // Label and valuable name check
        if (args.get(1).length() > 8 || args.get(3).length() > 8
                || !mA.matches() || mA1.lookingAt()
                || !mB.matches() || mB1.lookingAt())
            System.out.println("!Invalid valuable name!");
        else {
            // Type check
            if (args.get(2).equals("int")) {
                // Value check
                try {
                    simpleInt theInt = new simpleInt(Integer.parseInt(args.get(4)), args.get(3));
                    return new Simple(args.get(1), theInt, null);
                }
                catch (NumberFormatException nfe) {
                    System.out.println("!Invalid value!");
                }
            }
            else if (args.get(2).equals("bool")) {
                // Value check
                if (args.get(4).equals("true") || args.get(4).equals("TRUE")) {
                    simpleBool bool = new simpleBool(true, args.get(3));
                    return new Simple(args.get(1), null, bool);
                }
                else if (args.get(4).equals("false") || args.get(4).equals("FALSE")) {
                    simpleBool bool = new simpleBool(false, args.get(3));
                    return new Simple(args.get(1), null, bool);
                }
                else System.out.println("!Invalid value!");
            }
            else System.out.println("!Invalid type!");
        }
        return null;
    }

    /**
     * binary expression
     * format: binexpr expName expRef1 bop expRef2
     */
    // 写的有点混乱，而且没写完，你们谁改改看，我有点没读懂他的要求
    public static void binexpr(String[] args){
        Statement expName = new Statement(args[1]);

        String expr1 = args[2],expr2 = args[4],bop = args[3];

        // first check whether expRef 1 and 2 are label or variable
        if (searchVar(expr1)==-1 && searchLab(expr1)==-1) {       // e1 havnt stored in var or lab array
            System.out.println(expr1 + " not available.");
        } else if (!intOperator.contains(bop)) {
            System.out.println(bop + " not available.");
        } else if (searchVar(expr2)==-1 && searchLab(expr2)==-1) { // e2 havnt stored in var or lab array
            System.out.println(expr2 + " not available.");
        } else if (searchVar(expr1)>-1){      //e1 is var
            if (searchVar(expr2)>-1){           //e2 is also var

            }else{
                System.out.println("expRef2 need to be a Variable.");
            }
        } else if (searchLab(expr1)>-1){    //e1 is lab

        }

        // search is var in the storage array

    }

    // unexpr expName uop expRef1

    /*
vardef t1 int x 5
vardef t2 int y -5
vardef t3 bool z false
vardef t4 bool a true
unexpr t5 # t1
unexpr t6 ~ t1
unexpr t7 # t2
unexpr t8 ~ t2
unexpr t9 ! t3
unexpr t10 ! t3
unexpr t11 ! t4
unexpr t12 ! t4
     */
    public static final  String[] UN_EXPR = {"#", "~", "!"};
    public static Simple unexpr(List<String> args, ArrayList<Simple> simpleArr){
        if (args.size () != 4) {
            System.out.println ("err command");
            return null;
        }

        Simple oldVar = findVar (simpleArr, args.get (3)) ;
        String unaryExpr = args.get (2);

        if (simpleArr.size () == 0 ||  oldVar == null) {
            System.out.println ("var not found");
            return null;
        }
        Boolean isBool = isVarBool (oldVar);
        if (!containsExpr (unaryExpr)) {
            System.out.println ("expr is err");
            return null;
        } else if ("!".equals (unaryExpr) && !isBool) {
            System.out.println ("err 02 (操作符为！， 但var为int）");
            return null;
        } else if (!"!".equals (unaryExpr) && isBool) {
            System.out.println ("err 02 (操作符为#/~， 但var为bool）");
            return null;
        }
        String value;
        ArrayList<String> command = new ArrayList<> ();
        command.add ("vardef");
        command.add (args.get (1));
        command.add (isBool ? "bool" : "int");
        command.add (isBool ? oldVar.getBool ().getName () : oldVar.getInt ().getName ());
        if ("!".equals (unaryExpr)) {
            value = String.valueOf (!oldVar.getBool ().getValue ());
        } else if ("~".equals (unaryExpr)) {
            value = String.valueOf (-(oldVar.getInt ().getValue ()));
        } else if ("#".equals (unaryExpr)) {
            value = String.valueOf (+(oldVar.getInt ().getValue ()));
        } else {
            throw new RuntimeException ();
        }
        command.add (value);
        return vardef (command);
    }

    private static Simple findVar(ArrayList<Simple> simpleArr, String varName) {
        for (Simple simple : simpleArr) {
            if (simple.getLabel ().equals (varName)) {
                return simple;
            }
        }
        return null;
    }

    private static Boolean containsExpr (String expr) {
        for (String str : UN_EXPR) {
            if (str.equals (expr)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isVarBool (Simple var) {
        return var.getBool () != null && var.getInt () == null;
    }

    // assign lab varName expRef
    public static void assign(String[] args){
        if (searchVar(args[2])==-1) System.out.println(args[2]+ " not exist");
        Statement lab = new Statement(args[1]);
        int index = searchVar(args[2]);

        // expRxf can be a Statement or Variable or value(int/bool)
        if (searchLab(args[3])>-1){             // statement

        } else if (searchVar(args[3])>-1) {     // variable (and value?)
            if (varStorage.get(index).type.equals("int")) {
                varStorage.get(index).value = Integer.parseInt(args[3]);
            }else varStorage.get(index).value = Boolean.parseBoolean(args[3]);
        } else {                                // value

        }
    }


    /**
     * key：print label
     * value： var label
     */
    private static Map<String, String> printMap = new HashMap<> (16);

    // print print1 exp2
    /*
    vardef t1 int x 5
    print print1 t2
    print prin#1 t1
    print print1 t1
     */
    public static void print (List<String> args, ArrayList<Simple> simpleArr) {
        if (args.size () != 3) {
            System.out.println ("err command");
            return;
        }
        String label = args.get (1);
        Simple var = findVar (simpleArr, args.get (2));
        if (!verifyLabel (label) ) {
            System.out.println ("err input");
            return;
        }
        if (var == null) {
            System.out.println ("not found var : " + args.get (2));
            return;
        }

        printMap.put (label, var.getLabel ());
        System.out.println ("add print success");
    }

    public static void doPrint (String label, ArrayList<Simple> simpleArr) {
        if (!printMap.containsKey (label)) {
            System.out.println ("not found label : " + label);
            return;
        }

        String varLabel = printMap.get (label);
        Simple var = findVar (simpleArr, varLabel);
        if (var == null) {
            System.out.println ("not found var : " + varLabel);
            return;
        }
        boolean isBool = isVarBool (var);
        System.out.println ("["
                + "name : " + (isBool ? var.getBool ().getName () : var.getInt ().getName ()) + ", "
                + "value : " + (isBool ? var.getBool ().getValue () : var.getInt ().getValue ()) + "]");
    }

    private static boolean verifyLabel (String label) {
        Pattern p = Pattern.compile("[a-zA-Z\\d]+"), p1 = Pattern.compile("\\d");
        Matcher mA = p.matcher(label), mA1 = p1.matcher(label);
        return label.length () <= 8 && mA.matches () && !mA1.lookingAt ();
    }

   // defining skip statements
    public static void skip(String lab){
    }

    //search variables with varName return index
    public static int searchVar(String name){
        int index = 0;
        for(Variable var: varStorage){
            if (var.name.equals(name)) return index;
            index++;
        }
        return -1;
    }

    //search label though lab name and return index
    public static int searchLab(String name){
        int index = 0;
        for(Statement lab: labStorage){
            if (lab.label.equals(name)) return index;
            index++;
        }
        return -1;
    }

    // user input check? spelling? repeat lab or var names?
    public static boolean spellCheck(String word){
        return true;
    }
    public static boolean repeatNameCheck(String name){
        return true;
    }

}
