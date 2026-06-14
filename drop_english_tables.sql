-- Drop English-named duplicate tables if they exist
-- This will remove tables like 'product', 'payment', 'order_item', etc.
-- Use CASCADE to remove dependent objects.

DROP TABLE IF EXISTS "order_item" CASCADE;
DROP TABLE IF EXISTS "product" CASCADE;
DROP TABLE IF EXISTS "payment" CASCADE;
DROP TABLE IF EXISTS "payment_type" CASCADE;
DROP TABLE IF EXISTS "log_value_product" CASCADE;
DROP TABLE IF EXISTS "consumer_phone" CASCADE;
DROP TABLE IF EXISTS "consumer_address" CASCADE;
DROP TABLE IF EXISTS "consumer_order" CASCADE;
DROP TABLE IF EXISTS "consumer" CASCADE;
DROP TABLE IF EXISTS "stock" CASCADE;
DROP TABLE IF EXISTS "stock_address" CASCADE;
DROP TABLE IF EXISTS "delivery" CASCADE;

-- You can add/remove table names above depending on what exists in your DB.

SELECT 'done' as status;

