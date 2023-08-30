package designpattern.singleton;

import java.io.IOException;

/**
 * Runtime:每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
 * 调用dos命令：exec(String command)
 *
 * 运用了饿汉式的单例模式
 *
 * @author liushuai
 * @create 2020/5/4
 */
public class RuntimeDemo {
	public static void main(String[] args) throws IOException {
		Runtime r = Runtime.getRuntime();
		r.exec("notepad");   // 打开记事本
		// r.exec("calc");      // 打开计算器
		// r.exec("shutdown -s -t 10000");  // 10000s后关机
		// r.exec("shutdown -a");   // 取消关机
	}
}

/*
 * class Runtime {
 * 		private Runtime() {}
 * 		private static Runtime currentRuntime = new Runtime();
 * 		public static Runtime getRuntime() {
 *       	return currentRuntime;
 *   	}
 * }
 */