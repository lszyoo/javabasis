分组问题: 会话的分组

比如说, 进入一个网站以后, 可以连续的点击很多个页面, 后台会记录用户的行为日志;
如果T日上午连续点击几个页面后退出了网站, 直到第二天的下午才再次进入网站, 单单从时间线上来看, 昨天退出的那条日志跟今天进入的那条日志是连在一起的,
但这两条数据实际上并不是一个会话产生的, 如果需要对这样的数据进行分组, 将其分在两个不同的会话当中, 应该怎么做呢? 组与组之间的时间间隔应该是多少呢?

如下为电商公司用户访问时间数据  login_info
id      ts
1001    17523641234
1001    17523641256
1002    17523641278
1001    17523641334
1002    17523641434
1001    17523641534
1001    17523641544
1002    17523641634
1001    17523641638
1001    17523641654

时间间隔小于60秒, 则分为同一个组
1001    17523641234     1
1001    17523641256     1
1001    17523641334     2
1001    17523641534     3
1001    17523641544     3
1001    17523641638     4
1001    17523641654     4
1002    17523641278     1
1002    17523641434     2
1002    17523641634     3


分析:
这个问题可以看做: 判断连续的两条数据是否属于同一个组(时间有序), 这就涉及到当前行数据及前一行数据或者后一行数据的时间差是否在60秒以内,
               如果是就属于同一组, 反之就不是同一组

我们应该想到有两个窗口函数, 用来获取当前行数据的前N行或者后N行数据:
返回位于当前行的前n行的expr的值: LAG(expr, n, defval)
返回位于当前行的后n行的expr的值: LEAD(expr, n, defval)




with login_info as (
    select
        1001 as id,
        17523641234 as ts
    union all
    select
        1001 as id,
        17523641256 as ts
    union all
    select
        1001 as id,
        17523641334 as ts
    union all
    select
        1001 as id,
        17523641534 as ts
    union all
    select
        1001 as id,
        17523641544 as ts
    union all
    select
        1001 as id,
        17523641638 as ts
    union all
    select
        1001 as id,
        17523641654 as ts
    union all
    select
        1002 as id,
        17523641278 as ts
    union all
    select
        1002 as id,
        17523641434 as ts
    union all
    select
        1002 as id,
        17523641634 as ts
)
select
    id,
    ts,
    sum(if(ts - lag_ts < 60, 0, 1)) over(partition by id order by ts) as group_id
from
    (
        select
            id,
            ts,
            lag(ts, 1, 0) over(partition by id order by ts) as lag_ts
        from login_info
    ) t1
order by 1, 2

