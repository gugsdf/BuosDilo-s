import os
import psycopg2

# Read database connection info from environment variables for safety
DB_HOST = os.getenv('DATABASE_HOST', 'pg-138fd70a-pg-germinare.j.aivencloud.com')
DB_PORT = int(os.getenv('DATABASE_PORT', '16733'))
DB_USER = os.getenv('DATABASE_USER', 'avnadmin')
DB_PASSWORD = os.getenv('DATABASE_PASSWORD', None)
DB_NAME = os.getenv('DATABASE_NAME', 'eccormerce')

if not DB_PASSWORD:
    raise SystemExit('DATABASE_PASSWORD environment variable is required to run this script')

conn = psycopg2.connect(
    host=DB_HOST,
    port=DB_PORT,
    user=DB_USER,
    password=DB_PASSWORD,
    database=DB_NAME
)

cursor = conn.cursor()

tables = [
    'order_item', 'product', 'payment', 'payment_type', 'log_value_product',
    'consumer_phone', 'consumer_address', 'consumer_order', 'consumer',
    'stock', 'stock_address', 'delivery'
]

for t in tables:
    try:
        cursor.execute(f"DROP TABLE IF EXISTS {t} CASCADE")
        print(f"Dropped: {t}")
    except Exception as e:
        print(f"Error dropping {t}: {e}")

conn.commit()
cursor.close()
conn.close()
print('Done')

