from sklearn import svm
from sklearn import metrics
from sklearn import tree
from sklearn.svm import LinearSVC
from sklearn.ensemble import RandomForestClassifier
#from sklearn.linear_model import LogisticRegression
from sklearn.cross_validation import train_test_split
import os
import sys
import math
#准备数据
trainfile = "data/task2/YN_train_out_3.txt"
testfile = "data/task2/YN_dev_out_3.txt"
needTest = "data/task2/YN_test_out_2.txt"
f1 = open(trainfile)
f2 = open(testfile)
f3 = open(needTest)
#训练集合
train_set = []
#训练结果
train_target = []
#测试集合
test_set = []
#测试结果
test_target = []
#test.xml文件样例集合
need_set = []
#获得训练集合
for line1 in f1.readlines():
    sq = line1.split('\t')
    sq[-1] = float(sq[-1][0:-1])
    #print(sq[-1])
    if(sq[1]=='Yes'):
        train_target.append(1)        
    elif(sq[1]=='No'):
        train_target.append(-1)        
    else:
        train_target.append(0)       
    #.append(math.log(float(sq[7])+1))
    uni = []
    #uni.append(sq[2])
    #uni.append(float(sq[3])*float(sq[4]))
    #uni.append(sq[3])
    #uni.append(sq[4])
    uni.append(sq[7])
    #uni.append(sq[-1])
    train_set.append(uni)
 #获得测试集合   
for line2 in f2.readlines():
    sq = line2.split('\t')
    sq[-1] = sq[-1][0:-1]
    if(sq[1]=='Yes'):
        test_target.append(1)        
    elif(sq[1]=='No'):
        test_target.append(-1)        
    else:
        test_target.append(0)        
    #.append(math.log(float(sq[7])+1))
    uni = []
    #uni.append(sq[2])
    #uni.append(float(sq[3])*float(sq[4]))
    #uni.append(sq[3])
    #uni.append(sq[4])
    uni.append(sq[7])
    #uni.append(sq[-1])
    test_set.append(uni)
#获得最终testxml文件集合
for line3 in f3.readlines():
    sq = line3.split('\t')
    sq[-1] = float(sq[-1][0:-1])
    uni = []
    #uni.append(sq[2])
    #uni.append(float(sq[3])*float(sq[4]))
    #uni.append(sq[3])
    #uni.append(sq[4])
    uni.append(sq[7])
    #uni.append(sq[-1])
    need_set.append(uni)    
f1.close()
f2.close()
f3.close()
#使用SVM模型
#clf = svm.SVC(kernel='rbf',gamma=0.7, C = 1.0).fit(train_set, train_target)
#clf = svm.SVC.fit(train_set, train_target)
#使用随机森林模型
clf = RandomForestClassifier()
clf = clf.fit(train_set,train_target)
test_pre = clf.predict(test_set)
need_result = clf.predict(need_set)
#结果输出
f4 = open("data/task2/result_BEST.txt","w+")
for rst in need_result:
    if(rst==1):
        f4.write("Yse\n")
    elif(rst==-1):
        f4.write("No\n")
    else:
        f4.write("Unsure\n")
f4.flush()
f4.close()
print(metrics.classification_report(test_target, test_pre))
print("Confusion matrix")
print(need_result)
print(metrics.confusion_matrix(test_target, test_pre))


