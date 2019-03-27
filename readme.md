# 说明
~~~
一个基于maven的工具类包
~~~

# 工具类

### 1、日期

- 处理日期格式，转化不同格式；
- 两个日期的时间差getBeginEndDatetime

### 2、文件

- 复制/移动文件/
- 复制文件夹copyDir
- 判断文件是否为指定某类个/某些文件isFileNameEndsWith
- 统计文件夹内文件夹个数和文件个数getDirFileNumber
- 获取文件大小getFileSizeString
- 把某个/某些文件复制到指定文件夹下copyDirTpyesAll
- 删除指定类型文件copyDirTpyesAllInDir
- 删除文件夹removeDir
- 获取文件夹里所有文件的绝对路径getFileNames
- 判断两个文件是否相同equalsFile
- 把文件夹里相同的文件列出来getRepetitionFiles

### 3、Excel

- 读取和写入Excel，支持xls和xlsx格式

### 4、数学

- 组合combinationCnm
- 排列arrangeAnm
- 阶乘factorial
- 一元二次方程实数解oneYuanQuadraticEquations
- 一元二次方程复数解oneYuanQuadraticEquationsI
- 等差数列arithmeticSequence
- 等比数列geometricSequence
- 海伦公式helenFormula
- 判断是否为三角形triangle
- 二项分布binomialDistribution
- 最大公约数gcd
- 小数转分数（精度丢失下失效）getFraction
- 斐波那契数列getFibonacciSequence

### 5、MD5
- 获取经过MD5加密的密码getMD5

### 6、加解密文件
- 简单的加解密文件simple
- DES算法加解密des