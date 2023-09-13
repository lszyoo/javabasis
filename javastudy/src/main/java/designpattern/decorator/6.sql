信用卡欠款
表 credit_card_debt
start_date   end_date     amt
2023-01-01  2023-01-04    10
2023-01-02  2023-01-06    20
2023-01-05  2023-01-10    30

输出
2023-01-01  10
2023-01-02  30
2023-01-03  30
2023-01-04  30
2023-01-05  50
2023-01-06  50
2023-01-07  30

with credit_card_debt as (
    select
        '2023-01-01' as start_date,
        '2023-01-04' as end_date,
        10 as amt
    union all
    select
        '2023-01-02' as start_date,
        '2023-01-06' as end_date,
        20 as amt
    union all
    select
        '2023-01-05' as start_date,
        '2023-01-10' as end_date,
        30 as amt
)
select
    date_add(start_date, tf.i) as date_dtr,
    sum(amt) as amt
from credit_card_debt t1
    lateral view posexplode(split(space(datediff(end_date, start_date)), ' ')) tf as i, x
group by 1
;
