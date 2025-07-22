import react, { useState } from 'react';
import Modal from 'react-bootstrap/Modal'; 
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { deletePost } from '../api';

function DeleteConfirmationModal({ isOpen, onClose, onDeleteSuccess, postId}){
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');


    const handleConfirmDelete = async () =>{
        if (!password) {
            setError('비밀번호를 입력해주세요.');
            return;
        }
        try{
            await deletePost(postId,{password});
            onDeleteSuccess();

        }catch(error){
            console.error('게시글 삭제에 실패했습니다.', error);
            setError('게시글 삭제에 실패했습니다. 비밀번호를 확인해주세요.');
        }
    }

    const handleClose =() =>{
        setPassword('');
        setError('');
        onClose();
    }


    return (
        <Modal show={isOpen} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>게시물 삭제</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>이 게시물을 삭제하려면 비밀번호를 입력하세요.</p>
                <Form.Control
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="비밀번호"
                />
                {error && <p style={{ color: 'red' }}>{error}</p>}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    취소
                </Button>
                <Button variant="danger" onClick={handleConfirmDelete}>
                    삭제 확인
                </Button>
            </Modal.Footer>
        </Modal>
    );
}
export default DeleteConfirmationModal;