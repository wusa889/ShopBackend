INSERT INTO `product-manager`.categories (active, id, name)
VALUES (1, 1, 'Kategorie1');
INSERT INTO `product-manager`.categories (active, id, name)
VALUES (1, 2, 'Kategorie2');

INSERT INTO `product-manager`.products (active, price, stock, category_id, id, description, image, name, sku)
VALUES (1, 100, 10, 1, 1, 'Description of Product1', 'image1.jpg', 'Product1', 'SKU001');
INSERT INTO `product-manager`.products (active, price, stock, category_id, id, description, image, name, sku)
VALUES (1, 120, 15, 1, 2, 'Description of Product2', 'image2.jpg', 'Product2', 'SKU002');
INSERT INTO `product-manager`.products (active, price, stock, category_id, id, description, image, name, sku)
VALUES (1, 150, 5, 2, 3, 'Description of Product3', 'image3.jpg', 'Product3', 'SKU003');

INSERT INTO `product-manager`.user (admin, id, password, username)
VALUES (true, 1, '$2a$10$vwZ3...asV/b.Ob1Il.e8eiBm.xG95B1RNLp.tVQZdR8KBg1.3ISS', 'admin');
INSERT INTO `product-manager`.user (admin, id, password, username)
VALUES (false, 2, '$2a$10$vwZ3...asV/b.Ob1Il.e8eiBm.xG95B1RNLp.tVQZdR8KBg1.3ISS', 'user');