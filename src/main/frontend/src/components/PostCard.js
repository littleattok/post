import React, { useState } from 'react';
import DeleteConfirmationModal from './DeleteConfirmationModal';

function PostCard({post, onPostDeleted}){
    const [isDeleteModalOpen, setDeleteModalOpen] = useState(false);

    const handledDeleteModalOpen = () =>{
        setDeleteModalOpen(false);
        onPostDeleted();
    };

    return (
        <div className="post-card">
            {/*... 게시물 내용... */}
            <button onClick={() => setDeleteModalOpen(true)}>삭제</button>
            <DeleteConfirmationModal
                isOpen={isDeleteModalOpen}
                onClose={() => setDeleteModalOpen(false)}
                onDeleteSuccess={handleDeleteSuccess}
                postId={post.id}
            />
        </div>
    )
}