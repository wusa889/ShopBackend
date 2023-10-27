
-- Einfügen von zwei Produkten, die der ersten Kategorie angehören
INSERT INTO products (sku, active, name, image, description, price, stock,category_id) VALUES ('SKU001', 1, 'Product1', 'image1.jpg', 'Description of Product1', 100.0, 10, 1);
INSERT INTO products (sku, active, name, image, description, price, stock,category_id ) VALUES ('SKU002', 1, 'Product2', 'image2.jpg', 'Description of Product2', 120.0, 15, 1);

-- Einfügen von einem Produkt, das der zweiten Kategorie angehört
INSERT INTO products (sku, active, name, image, description, price, stock,category_id ) VALUES ('SKU003', 1, 'Product3', 'image3.jpg', 'Description of Product3', 150.0, 5, 2);