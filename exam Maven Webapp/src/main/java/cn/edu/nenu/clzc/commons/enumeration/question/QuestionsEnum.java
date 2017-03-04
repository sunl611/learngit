package cn.edu.nenu.clzc.commons.enumeration.question;

public enum QuestionsEnum {
	
	ChoiceQuestion("01", "选择题"),
	
	FillInQuestion("02", "填空题"),
	
	ClozeTestQuestion("03", "完形填空题");
	
	
	
	
	
	
	private String prefix;
	
	private String type;
	
	
	private QuestionsEnum(String prefix, String type) {
		this.prefix = prefix;
		this.type = type;
	}
	
	private String generatorQuestionNumber(){
		int random = 0;
		random = new Double(Math.random()*9000).intValue()+1000;
		
		return prefix + random;
	}
	public String generatorQuestionNumber(String questionNum){
		if (questionNum == null || questionNum.length() != 6) {
			throw new IllegalArgumentException();
		}
		int random = 0;
		random = new Double(Math.random()*9000).intValue()+1000;
		
		return questionNum.substring(0, 2) + random;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String  getQuestionNum(String type){
		QuestionsEnum[] questionsEnums = QuestionsEnum.values();
		
		QuestionsEnum tmpEnum = null;
		for (int i = 0; i < questionsEnums.length; i++) {
			QuestionsEnum questionsEnum = questionsEnums[i];
			
			if (type != null){
				if (type.equals(questionsEnum.getPrefix())) {
					tmpEnum = questionsEnum;
					break;
				}
			} 
		}
		
		return tmpEnum.generatorQuestionNumber();
	}
	

}
