连续问题:

表名: carbons
如下数据为蚂蚁森林中用户领取的减少碳排放量
id        dt             lowcarbon
1001    2021-12-12        123
1001    2021-12-13        43
1001    2021-12-13        45
1001    2021-12-13        23
1001    2021-12-14        230
1001    2021-12-15        23
1002    2021-12-12        45
1002    2021-12-14        45
1002    2021-12-15        45
.......
找出连续3天及以上减少碳排放量在100以上的用户

分析:
遇到这类问题, 我们可以用等差数列法来求解, 何为等差数列法?
等差数列法: 两个等差数列如果等差相同, 则相同位置的数据相减到的结果相同

比如有一个等差数列: 2 3 4 5 6 7 8 对他们排序后的顺序为 1 2 3 4 5 6 7, 转成列展示为:
num     rank    相同位置相减得(flag)
2        1          1
3        2          1
4        3          1
5        4          1
6        5          1
7        6          1
8        7          1
此时按照flag分组求和, 就得到连续的条数
再如:
num     rank    相同位置相减得(flag)
2        1          1
3        2          1
4        3          1
7        4          3
8        5          3
9        6          3
10       7          3
14       8          6
15       9          6
按照flag分组求和, 就得到连续的条数有三组: 234(3), 789 10(4), 14 15(2)
基于这样的结果再做一次筛选就能得到想要的答案



with carbons as (
    select
        1001 as id,
        '2021-12-12' as dt,
        123 as lowcarbon
    union all
    select
        1001 as id,
        '2021-12-13' as dt,
        43 as lowcarbon
    union all
    select
        1001 as id,
        '2021-12-13' as dt,
        45 as lowcarbon
    union all
    select
        1001 as id,
        '2021-12-13' as dt,
        23 as lowcarbon
    union all
    select
        1001 as id,
        '2021-12-14' as dt,
        230 as lowcarbon
    union all
    select
        1001 as id,
        '2021-12-15' as dt,
        23 as lowcarbon
    union all
    select
        1002 as id,
        '2021-12-12' as dt,
        45 as lowcarbon
    union all
    select
        1002 as id,
        '2021-12-14' as dt,
        45 as lowcarbon
    union all
    select
        1002 as id,
        '2021-12-15' as dt,
        45 as lowcarbon
), carbon_dt as (
    select
        id,
        dt,
        sum(lowcarbon) as lowcarbon
    from carbons
    group by 1, 2
    having lowcarbon > 100
), carbon_dt_temp as (
    select
        id,
        dt,
        date_sub(dt, rn) as dt_temp
    from
        (
            select
                id,
                dt,
                row_number() over(partition by id order by dt asc) as rn
            from carbon_dt
        ) t1
)
select
    distinct id
from
    (
        select
            id,
            dt_temp
        from carbon_dt_temp
        group by 1, 2
        having count(1) > 3
    ) t1
;