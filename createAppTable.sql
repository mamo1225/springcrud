set search_path to springcrud;

create table order (
  order_id text
  , orderer text
  , product_name text
  , order_quantity numeric
  , order_date varchar(10)
  , adress varchar(50)
  , tel varchar(20)
  , version bigint
  , constraint sample_order_pkc primary key (order_id)
) ;

comment on table sample_order is 'サンプルアプリ用注文テーブル';
comment on column sample_order.order_id is '注文ID';
comment on column sample_order.orderer is '注文者';
comment on column sample_order.product_name is '商品名';
comment on column sample_order.order_quantity is '注文数';
comment on column sample_order.order_date is '注文日';
comment on column sample_order.adress is '住所';
comment on column sample_order.tel is '電話番号';
comment on column sample_order.version is 'バージョン';





■SQLite
create table orderinfo ( order_id TEXT, orderer TEXT, product_name TEXT, order_quantity INTEGER, order_date TEXT, adress TEXT, tel TEXT, version INTEGER, PRIMARY KEY (order_id));
