-- Insérer d'abord les catégories avec des IDs fixes
INSERT INTO category (id, description, name) VALUES
                                                 (1, 'Electronics and gadgets', 'Electronics'),
                                                 (2, 'Fresh and organic food items', 'Groceries'),
                                                 (3, 'Fashion and clothing items', 'Clothing'),
                                                 (4, 'Home and living essentials', 'Home & Living');

-- Insérer ensuite les produits, en s'assurant que category_id correspond bien aux IDs insérés
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
                                                                                        (nextval('product_seq'), 'Smartphone with 128GB storage and 6GB RAM', 'Smartphone X', 50, 699.99, 1),
                                                                                        (nextval('product_seq'), 'Wireless noise-canceling headphones', 'Headphones Pro', 30, 199.99, 1),
                                                                                        (nextval('product_seq'), 'Organic whole wheat bread', 'Whole Wheat Bread', 100, 2.99, 2),
                                                                                        (nextval('product_seq'), 'Fresh organic apples (1kg)', 'Organic Apples', 80, 3.49, 2),
                                                                                        (nextval('product_seq'), 'Men''s cotton T-shirt (size M)', 'Cotton T-shirt', 60, 15.99, 3),
                                                                                        (nextval('product_seq'), 'Women''s jeans (size L)', 'Denim Jeans', 40, 39.99, 3),
                                                                                        (nextval('product_seq'), 'Wooden dining table with 4 chairs', 'Dining Table Set', 10, 499.99, 4),
                                                                                        (nextval('product_seq'), 'LED table lamp with adjustable brightness', 'Table Lamp', 25, 29.99, 4);
