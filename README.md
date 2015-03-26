每次做一个项目都会遇到导出Excel的需求，每次都不能安心的写业务代码，因为以前总把生成Excel的逻辑耦合在业务代码中。<br />
这个项目旨在通过配置和抽象，将导出逻辑最简化。<br />

现在，你只需要配置一个Json文件，像这样：
<code>
<pre>
{
    "tableName": "student",
    "condition": {
        "classId": "classId",
        "time": "hp_cal_dt"
    },
    "definition": [
        {
            "key": "rank",
            "desc": "排名百分比",
            "handlers": [
                "Decimal|2",
                "Percent"
            ]
        }
    ]
}
</pre>
</code>
<br />
指定一个表名，给出查询条件，给出Excel的定义即可。<br />
其中：key对应数据库字段名，desc为Excel表头，Handlers表对数据的处理，
如Decimal|2表示取到小数点后两位，Percent表示对数据取百分比。



