try:
        import xml.etree.cElementTree as ET
except ImportError:
        import xml.etree.ElementTree as ET
import sys
import math
import types
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords
from nltk.corpus import wordnet
#去除停用词
#test 句子； sq  停用词表
def stepwords(test,sq):
        english_stopwords = stopwords.words('english')        
        #print(sq)
        english_stopwords.extend(sq)
        #扩展停用词库
        english_stopwords.append('?')
        english_stopwords.append('.')
        english_stopwords.extend([',',"\"","\'s","\'ve",'!',':',';','(',')','&',
                                  '...','ok','hi','>','/>','<','--','..','-',"\'\'",'``'])
        #print(english_stopwords)
        text_tokenized = [word for word in word_tokenize(test)]
        #获得去除停用词后的主要词
        filter_words = [word for word in text_tokenized if word.lower() not in english_stopwords]
        print(filter_words)
        return filter_words;
#句子相似度
#d、f 表示两个去除停用词后的句子
def similarity(d,f):
        dseq = d
        fseq = f
        #ddd 、fff 表示相似度向量
        ddd = []
        fff = [1]*len(fseq)
        for word1 in dseq:
                sim = 0
                for word2 in fseq:
                        wordset1 = wordnet.synsets(word1.lower())
                        wordset2 = wordnet.synsets(word2.lower())
                        #查找两个词的名词性最短距离
                        if(len(wordset1)>0 and len(wordset2)>0):
                                path = wordset1[0].shortest_path_distance(wordset2[0])
                                #使用指数函数表示距离越近，相似度越大
                                if(type(path) == int and math.exp(-path)>sim):
                                        sim = math.exp(-path)
                fff.append(sim);
        for word1 in fseq:
                sim = 0
                for word2 in dseq:                    
                        wordset1 = wordnet.synsets(word1.lower())
                        wordset2 = wordnet.synsets(word2.lower())
                        #查找两个词的名词性最短距离
                        if(len(wordset1)>0 and len(wordset2)>0):
                                path = wordset1[0].shortest_path_distance(wordset2[0])
                                #使用指数函数表示距离越近，相似度越大
                                if(type(path) == int and math.exp(-path)>0.1 and math.exp(-path)>sim):
                                        sim = math.exp(-path)
                ddd.append(sim)
        ddd.extend([1]*len(dseq))
        cos=0.0
        maxd=0.0
        maxf = 0.0
        #余弦值获得句子的相似度
        for i in range(len(ddd)):
                maxd = maxd+float(ddd[i])*float(ddd[i])
                maxf = maxf+float(fff[i])*float(fff[i])
                cos = cos +ddd[i]*fff[i]
        a = math.sqrt(maxd)*math.sqrt(maxf)
        if(a==0):
                b = 0
        else:
                b = cos/a
        print(b)
        return b;
#统计确定词的概率
def countYesAndNoWords(s):
    yesNum =['yes','yeah','ok','good','can','will','sure','do','did','does','y','okay','could']
    #['no','nope','not','without','none','n\'t','wrong','bad']
    Yn = 0
    Nn = 0
    yes_word = [word.lower() for word in word_tokenize(s)]
    no_word = s.split()
    for word in no_word:
        if(word.lower()=='no'):
                Nn = Nn+1
        elif(word.lower()=='nope'):
                Nn = Nn+1
        elif(word.lower()=='without'):
                Nn = Nn+1
        elif(word.lower()=='none'):
                Nn = Nn+1
        elif(word.lower()=='wrong'):
                Nn = Nn+1
        elif(word.lower()=='bad'):
                Nn = Nn+1
        elif(word.lower().find('n\'t')>=0):
                Nn = Nn+1
        elif(word.lower().find('nt')>=0):
                Nn = Nn+1
    for word in yes_word:
        for y in yesNum:
            if(word.lower()==y):
                Yn = Yn+1
    if(Nn==0):
            Yn = Yn + 1
    return Yn,Nn;
#读取停用词表
fst = open("data/stopwords.txt")
sq = []
for line in fst.readlines():
    sp = line.split()
    sq.extend(sp)
fst.close()
#解析xml文件
try:
        tree = ET.parse("data/xml/train.xml")
        root = tree.getroot()
except Exception as e:
        print("Errot:parse file xml failed!")
        sys.exit(1)
#预处理结果输出文件地址
fp = open("data/task2/YN_train_out_4.txt",'w+')
print("*"*50)
for Question in root.findall('Question'):        
        #此部分用于提取YES_NO类型问题与答案,其他时，请注释
        if(Question.get("QTYPE") != "YES_NO"):
                continue
        QSubject = Question.find('QSubject')
        QBody = Question.find('QBody')
        QSlen = len(QSubject.text)
        QBlen = len(QBody.text)
        #answer txt length
        answerAvgLen = 0
        #answer words lenth
        answerAvgWords = 0
        #Q-A average similarity
        avgSim = 0
        #answer numbers
        comNum = 0
        #肯定词出现数量
        yesNum = 0
        #否定词出现数量
        NoNum = 0
        QSubEl = stepwords(QSubject.text,sq)
        QBoEl = stepwords(QBody.text,sq)
        QBELen = len(QBoEl)
        #标记为No的答案数
        NoSentence =0
        #标记为Yes的答案数
        YesSentence = 0
        #标记为UnSure的答案数
        UnSentence = 0
        #去除停用词为空，则说明句子本身长度小，直接使用
        if(len(QSubEl)==0):
                QSubEl = QSubject.text.split()
        QBoEl = stepwords(QBody.text,sq)
        Comments = Question.findall('Comment')
        for comment in Comments:
            if(comment.get("CGOLD_YN")=="Yes"):
                    YesSentence = YesSentence +1
            elif(comment.get("CGOLD_YN")=="No"):
                    NoSentence = NoSentence +1
            else:
                    UnSentence = UnSentence +1
            if(comment.get("CUSERID")==Question.get("QUSERID")):
                        continue
            comNum = comNum + 1
            CSubject = comment.find('CSubject')
            CBody = comment.find('CBody')
            CBoEl = stepwords(CBody.text,sq)
            yes,no = countYesAndNoWords(CBody.text)
            #CboEl为0，就用其本身代替
            if(len(CBoEl)==0):
                CBoEl = [word for word in word_tokenize(CBody.text)]
            answerAvgWords = answerAvgWords + len(CBoEl)
            avgSim = avgSim + similarity(QBoEl,CBoEl)
            answerAvgLen = answerAvgLen + len(CBody.text)
            yesNum = yesNum+yes
            NoNum = NoNum+no
        if(comNum ==0):
            #avgSim = avgSim
            answerAvgLen = answerAvgLen
            answerAvgWords = answerAvgWords
        else:
            #avgSim = avgSim/comNum
            answerAvgLen = answerAvgLen/comNum
            answerAvgWords = answerAvgWords/comNum
        QFlag = -2
        if(YesSentence>=NoSentence and YesSentence>= UnSentence):
                QFlag = 1
        elif(YesSentence<NoSentence and NoSentence>= UnSentence):
                QFlag = -1
        elif(UnSentence>YesSentence and UnSentence>NoSentence):
                QFlag = 0
        fp.write(Question.get("QID")+'\t'+Question.get("QGOLD_YN")+
                 '\t'+str(avgSim)+'\t'+str(yesNum)+
                 '\t'+str(NoNum)+'\t'+str(QBELen)+
                 '\t'+str(answerAvgLen)+'\t'+str(answerAvgWords)+
                 '\t'+str(answerAvgLen/QBELen)+'\t'+str(QFlag)+'\n')
fp.flush()
fp.close()

