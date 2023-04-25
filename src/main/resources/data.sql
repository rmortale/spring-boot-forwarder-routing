INSERT INTO routing (id, service_id, service_version, system, environment, target_queue)
VALUES (1, 'aba_customer_created', '1.0', 'ABA', 'DEVT', 'bab.to.queue.01');

INSERT INTO routing (id, service_id, service_version, system, environment, target_queue)
VALUES (2, 'aba_customer_deleted', '1.0', 'ABA', 'DEVT', 'bab.to.queue.01');

INSERT INTO routing (id, service_id, service_version, system, environment, target_queue)
VALUES (3, 'bab_data_cleared', '1.0', 'BAB', 'DBAB1', 'aba.to.queue.01');

INSERT INTO routing (id, service_id, service_version, system, environment, target_queue)
VALUES (4, 'bab_data_cleared', '1.0', 'BAB', 'DBAB1', 'abb.to.queue.01');
