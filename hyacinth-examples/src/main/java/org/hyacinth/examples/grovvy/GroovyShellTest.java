package org.hyacinth.examples.grovvy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class GroovyShellTest {
	
	/**
	 * 简答脚本执行
	 * @throws Exception
	 */
	public static void evalScriptText() throws Exception{
		//groovy.lang.Binding
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		
		binding.setVariable("name", "zhangsan");
		shell.evaluate("println 'Hello World! I am ' + name;");
		//在script中,声明变量,不能使用def,否则scrope不一致.
		shell.evaluate("date = new Date();");
		Date date = (Date)binding.getVariable("date");
		System.out.println("Date:" + date.getTime());
		//以返回值的方式,获取script内部变量值,或者执行结果
		//一个shell实例中,所有变量值,将会在此"session"中传递下去."date"可以在此后的script中获取
		Long time = (Long)shell.evaluate("def time = date.getTime(); return time;");
		System.out.println("Time:" + time);
		binding.setVariable("list", new String[]{"A","B","C"});
		//invoke method
		String joinString = (String)shell.evaluate("def call(){return list.join(' - ')};call();");
		System.out.println("Array join:" + joinString);
		shell = null;
		binding = null;
	}
	
	/**
	 * 以面向"过程"的方式运行脚本
	 * @throws Exception
	 */
	public static void evalScript() throws Exception{
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		//直接方法调用
		//shell.parse(new File(//))
		Script script = shell.parse(new File("C://Java/Eworkspace/test/hyacinth/hyacinth-examples/src/main/resources/script"));
		String joinString = (String)script.invokeMethod("join", new String[]{"A1","B2","C3"});
		System.out.println(joinString);
		////脚本可以为任何格式,可以为main方法,也可以为普通方法
		//1) def call(){...};call();
		//2) call(){...};
		script = shell.parse("static void main(String[] args){i = i * 2;}");
		script.setProperty("i", new Integer(10));
		script.run();//运行,
		System.out.println(script.getProperty("i"));
		//the same as
		System.out.println(script.getBinding().getVariable("i"));
		script = null;
		shell = null;
	}
	
	public static void evalScriptEngine() throws Exception{
		ScriptEngineManager factory = new ScriptEngineManager();
		//每次生成一个engine实例
		ScriptEngine engine = factory.getEngineByName("groovy");
		System.out.println(engine);
		assert engine != null;
		//javax.script.Bindings
		Bindings binding = engine.createBindings();
		binding.put("date", new Date());
		File file = new File("C://Java/Eworkspace/test/hyacinth/hyacinth-examples/src/main/resources/script");
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file)); // 建立一个输入流对象reader  
	    BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	    StringBuffer sb = new StringBuffer();
	    String line = br.readLine();  
	    while (line != null) {  
	    	sb.append(line).append("\n");
	        line = br.readLine(); // 一次读入一行数据  
	    }
		//如果script文本来自文件,请首先获取文件内容
//		engine.eval("def getTime(){return date.getTime();}",binding);
//		engine.eval("def sayHello(name,age){return 'Hello,I am ' + name + ',age' + age;}");
	    engine.eval(sb.toString(), binding);
		Long time = (Long)((Invocable)engine).invokeFunction("getTime", null);
		System.out.println(time);
		String message = (String)((Invocable)engine).invokeFunction("sayHello", "zhangsan",new Integer(12));
		System.out.println(message);
	}
	
	public static void main(String[] args) throws Exception {
//		GroovyShellTest.evalScriptText();
		GroovyShellTest.evalScript();
//		while (true) {
//			GroovyShellTest.evalScriptEngine();
//			Thread.sleep(10000);
//		}
		
	}
}
