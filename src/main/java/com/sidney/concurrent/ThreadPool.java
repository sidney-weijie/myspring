package com.sidney.concurrent;

public interface ThreadPool <Job extends Runnable> {
	//ִ��һ��job�����job��Ҫʵ��runnable
	void execute(Job job);
	//�ر��̳߳�
	void shutdown();
	//���ӹ������߳�
	void addWorkers(int num);
	//���ٹ������߳�
	void removeWorkers(int num);
	//�õ����ڵȴ�ִ�е���������
	int getJobSize();
}
