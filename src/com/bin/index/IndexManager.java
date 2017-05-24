package com.bin.index;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;

public class IndexManager {
 
	public void IndexCreate() throws Exception{
		List<Document> docLsit = new ArrayList<Document>();
		//ָ��Ŀ¼�ļ�
		File dir= new File("D:\\demo");
		//ѭ���������ļ��д� ȡ�� �ļ�
		for(File file :dir.listFiles()){
			//��ȡ�ļ����е����ļ�������
			String fileName =file.getName();
			//��ȡ�ļ�������
			String fileContent =FileUtils.readFileToString(file);
			//��ȡ�ļ��Ĵ�С
			Long fileSize = FileUtils.sizeOf(file);
			//�����ı�����
			Document docment =new Document();
			
			//��ȡ��
			//����һ����ֵ
			//������������
			//���������Ƿ�洢
			TextField nameFile =new TextField("fileName", fileName, Store.YES);
			TextField Contentfile =new TextField("fileContent", fileContent, Store.YES);
			TextField sizeFile =new TextField("fileSize", fileSize.toString(), Store.YES);
			 
			//������������ĵ���
			docment.add(nameFile);
			docment.add(Contentfile);
			docment.add(sizeFile);
			
			//�������ĵ��ŵ��ĵ�������
			docLsit.add(docment);
		}
	}
}
