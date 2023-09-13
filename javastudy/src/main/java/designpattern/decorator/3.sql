间隔连续问题:

某游戏公司记录的用户每日登录数据 login_info
id     dt
1001 2021-12-12
1001 2021-12-13
1001 2021-12-14
1001 2021-12-16
1001 2021-12-19
1001 2021-12-20
1002 2021-12-12
1002 2021-12-16
1002 2021-12-17
计算每个用户最大的连续登录天数, 可以间隔一天, 解释: 如果一个用户在 1,3,5,6 登录游戏, 则视为连续 6 天登录

分析:
    类似题2, 将间隔一天的日期数据放到一组, 然后用最大日期减去最小日期加 1 即可

with login_info as (
    select
        1001 as id,
        '2021-12-12' as dt
    union all
    select
        1001 as id,
        '2021-12-13' as dt
    union all
    select
        1001 as id,
        '2021-12-14' as dt
    union all
    select
        1001 as id,
        '2021-12-16' as dt
    union all
    select
        1001 as id,
        '2021-12-19' as dt
    union all
    select
        1001 as id,
        '2021-12-20' as dt
    union all
    select
        1002 as id,
        '2021-12-12' as dt
    union all
    select
        1002 as id,
        '2021-12-16' as dt
    union all
    select
        1002 as id,
        '2021-12-17' as dt
), login_group as (
    select
        id,
        dt,
        sum(if(datediff(dt, lag_dt) <= 2, 0, 1)) over(partition by id order by dt) as group_id
    from
        (
            select
                id,
                dt,
                lag(dt, 1, '1970-01-01') over(partition by id order by dt) lag_dt
            from login_info
        ) t1
)
select
    id,
    max(res) as res
from
    (
        select
            id,
            group_id,
            datediff(max(dt), min(dt)) + 1 as res
        from login_group
        group by 1, 2
    ) t1
group by id